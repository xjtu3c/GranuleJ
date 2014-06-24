#include <iostream>
#include <stdio.h>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <list>
#include "MethodInvokeAgent.h"
#include "jvmti.h"
#include <algorithm>
#include <time.h>
#include "HashTable.h"
#include "jvmDll.h"

using namespace std;

jvmtiEnv* MethodInvokeAgent::m_jvmti = 0;
jrawMonitorID MethodInvokeAgent::m_lock;
char* MethodInvokeAgent::m_filter = 0;
vector<string> MethodInvokeAgent::userclasses;
//vector<string> MethodInvokeAgent::intercept_m;
set<string> MethodInvokeAgent::methods;
set<string> MethodInvokeAgent::m_names;
replace_m_c MethodInvokeAgent::rep_m_c;
bool MethodInvokeAgent::isReplace=false;
int MethodInvokeAgent::loop_size=0;
map<string,int> MethodInvokeAgent::c_tab;
list<restricted_method> MethodInvokeAgent::restricted_methods;
list<method_info> MethodInvokeAgent::method_infos;
list<jmethodID> MethodInvokeAgent::method_frames;
list<fit_granule> MethodInvokeAgent::fit_granules;
FieldInfo* MethodInvokeAgent::fInfo;
int MethodInvokeAgent::total_count;
StateInfo ** MethodInvokeAgent::st_map;
int MethodInvokeAgent::st_size=0;
int MethodInvokeAgent::obj_count=0;

HashTable MethodInvokeAgent::instances;
bool MethodInvokeAgent::allCheck=false;
char* MethodInvokeAgent::replace_g_n=NULL;

string MethodInvokeAgent::shadowclass;

clock_t MethodInvokeAgent::start;
clock_t MethodInvokeAgent::end;

//方法的初始化
jmethodID MethodInvokeAgent::getInstance_GVMInit=NULL;
jmethodID MethodInvokeAgent::contextTearDown_GVMInit=NULL;
jclass MethodInvokeAgent::gvmInit_class=NULL;

jmethodID MethodInvokeAgent::getInstance_GranulePolling=NULL;
jmethodID MethodInvokeAgent::getPollingResult_GranulePolling=NULL;
jclass MethodInvokeAgent::granulePolling_class=NULL;

jclass MethodInvokeAgent::invoke_class=NULL;
jmethodID MethodInvokeAgent::loadGranule_GranuleLoader=NULL;

jclass MethodInvokeAgent::xmlParser_class=NULL;
jmethodID MethodInvokeAgent::replaceGranuleTree_XmlParser=NULL;
jmethodID MethodInvokeAgent::getLatestShadowClass_XmlParser=NULL;
jmethodID MethodInvokeAgent::getFitnessGranuleSet_XmlParser=NULL;
jmethodID MethodInvokeAgent::interceptMethods_XmlParser=NULL;
jmethodID MethodInvokeAgent::havaShadowClass_XmlParser=NULL;

jclass MethodInvokeAgent::granuleTree_class=NULL;
jmethodID MethodInvokeAgent::getGranuleAllChildren_granuleTree=NULL;
jmethodID MethodInvokeAgent::getGranuleParents_granuleTree=NULL;

jclass MethodInvokeAgent::updateInstances_class=NULL;
jmethodID MethodInvokeAgent::createNewInstance_UpdateInstances=NULL;
jobject MethodInvokeAgent::obj=NULL;
char* MethodInvokeAgent::granule_file="TestGranuleTree.xml"; 


MethodInvokeAgent::~MethodInvokeAgent() throw(AgentException)
{

	m_jvmti->Deallocate(reinterpret_cast<unsigned char*>(m_filter));
}

void MethodInvokeAgent::Init(JavaVM *vm) const throw(AgentException){
	jvmtiEnv *jvmti = 0;
	jint ret = (vm)->GetEnv(reinterpret_cast<void**>(&jvmti), JVMTI_VERSION_1_0);
	if (ret != JNI_OK || jvmti == 0) {
		throw AgentException(JVMTI_ERROR_INTERNAL);
	}
	m_jvmti = jvmti;
}


void MethodInvokeAgent::ParseOptions(const char* str) const throw(AgentException)
{
	if (str == 0)
		return;
	const size_t len = strlen(str);
	if (len == 0) 
		return;   

	jvmtiError error;
	error = m_jvmti->Allocate(len + 1,reinterpret_cast<unsigned char**>(&m_filter));
	CheckException(error);
	strcpy(m_filter, str);

}

void MethodInvokeAgent::AddCapability() const throw(AgentException)
{

	jvmtiCapabilities caps;
	memset(&caps, 0, sizeof(caps));
	caps.can_tag_objects = 1; 
	caps.can_access_local_variables = 1;
	caps.can_force_early_return=1;  
	caps.can_redefine_classes = 1;

	jvmtiError error = m_jvmti->AddCapabilities(&caps);
	CheckException(error);

}

void MethodInvokeAgent::RegisterEvent() const throw(AgentException)
{

	jvmtiEventCallbacks callbacks;
	memset(&callbacks, 0, sizeof(callbacks));
	callbacks.VMInit= &MethodInvokeAgent::HandleVMInit;
//	callbacks.VMDeath = &MethodInvokeAgent::HandleVMDeath;
//	callbacks.ThreadEnd = &MethodInvokeAgent::HandleThreadEnd;


	jvmtiError error;
	error = m_jvmti->SetEventCallbacks(&callbacks, static_cast<jint>(sizeof(callbacks)));
	CheckException(error);

	error = m_jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_VM_INIT, (jthread)NULL);
//	error = m_jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_VM_DEATH, (jthread)NULL);
//	error = m_jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_THREAD_END, (jthread)NULL);

	error = m_jvmti->CreateRawMonitor("agent data", &m_lock);
	check_jvmti_error(m_jvmti, error, "Cannot create raw monitor");	
}

void MethodInvokeAgent::check_jvmti_error(jvmtiEnv *jvmti, jvmtiError error, const char *msg){
	if (error != JVMTI_ERROR_NONE){
		char *name = NULL;
		m_jvmti->GetErrorName(error, &name);
		fprintf(stderr, "ERROR: JVMTI: %d(%s): %s\n", error, (name == NULL ? "Unknown" : name), (msg == NULL ? "" : msg));
	}
}

/* Enter agent monitor protected section */
void MethodInvokeAgent::enterAgentMonitor(jvmtiEnv *jvmti)
{
	jvmtiError err = m_jvmti->RawMonitorEnter(m_lock);
	check_jvmti_error(m_jvmti, err, "Cannot enter with raw monitor");
}

/* Exit agent monitor protected section */
void MethodInvokeAgent::exitAgentMonitor(jvmtiEnv *jvmti)
{
	jvmtiError err = m_jvmti->RawMonitorExit(m_lock);
	check_jvmti_error(m_jvmti, err, "Cannot exit with raw monitor");
}

void JNICALL MethodInvokeAgent::HandleVMInit(jvmtiEnv *jvmti_env, JNIEnv *env, jthread thr)
{
	enterAgentMonitor(m_jvmti); 
	{
		jclass JavaClass;
		JavaClass=env->FindClass("granulej/lang/GVMInit"); 

		if(JavaClass!=0)
		{
			jmethodID methodID=env->GetStaticMethodID(JavaClass,"vmStart","()Z");
			if(methodID!=0){
				jboolean flag=(jboolean)env->CallStaticBooleanMethod(JavaClass,methodID);
				allCheck=static_cast<bool>(flag);
				//allCheck=true;
			}
		}
		env->DeleteLocalRef(JavaClass);

		MethodInvokeAgent::initMethods(jvmti_env,env);
	}
	exitAgentMonitor(m_jvmti);
}

void JNICALL MethodInvokeAgent::HandleVMDeath(jvmtiEnv *jvmti_env,
											  JNIEnv* jni_env)
{
	printLog("handleVMDeath","end!");
}

void JNICALL MethodInvokeAgent::HandleThreadEnd(jvmtiEnv *jvmti_env,
		  JNIEnv* jni_env,
		  jthread thread)
{
	jvmtiThreadInfo info_ptr;
	jvmti_env->GetThreadInfo(thread,&info_ptr);
	cout<<info_ptr.name<<endl;
	if(strcmp(info_ptr.name,"main")==0)
	{
		jni_env->CallVoidMethod(jni_env->CallStaticObjectMethod(gvmInit_class,getInstance_GVMInit),contextTearDown_GVMInit);
	}

	printLog("HandleThreadEnd","end!");
}

void MethodInvokeAgent::printLog(string info,string msg)
{
	cout<<info<<": "<<msg<<endl;
}

void MethodInvokeAgent::initMethods(jvmtiEnv *jvmti_env, JNIEnv *env)
{
	jclass gvmInit_class=env->FindClass("granulej/lang/GVMInit");
	if(gvmInit_class==NULL)
	{
		printLog("GVMInit","初始化失败");
	}
	getInstance_GVMInit=env->GetStaticMethodID(gvmInit_class,"getInstance","()Lgranulej/lang/GVMInit;");
	if(getInstance_GVMInit==NULL)
	{
		printLog("getInstance_GVMInit","初始化失败");
	}
	contextTearDown_GVMInit=env->GetMethodID(gvmInit_class,"ContextTearDown","()V");
	if(contextTearDown_GVMInit==NULL)
	{
		printLog("contextTearDown_GVMInit","初始化失败");
	}
}

