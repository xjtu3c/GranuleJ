#include <iostream>

#include "MethodInvokeAgent.h"
#include "jvmti.h"
#include "jni.h"
#include <time.h>

using namespace std;

JNIEXPORT jint JNICALL Agent_OnLoad(JavaVM *vm, char *options, void *reserved)
{
	MethodInvokeAgent::start=clock();
    try{
        
        MethodInvokeAgent* agent = new MethodInvokeAgent();
		agent->Init(vm);
        agent->ParseOptions(options);
        agent->AddCapability();
        agent->RegisterEvent();	
        
    } catch (AgentException& e) {
        cout << "Error when enter HandleMethodEntry: " << e.what() << " [" << e.ErrCode() << "]";
		return JNI_ERR;
	}

	cout<<"Init GVM.dll success!!"<<endl;
    
	return JNI_OK;
}

JNIEXPORT void JNICALL Agent_OnUnload(JavaVM *vm)
{
	MethodInvokeAgent::end=clock();
	cout<<"Run time:"<<MethodInvokeAgent::end-MethodInvokeAgent::start<<endl;

}
