#include <string>
#include <vector>
#include <set>
#include <map>
#include <list>
#include <time.h>
#include "HashTable.h"
#include "jvmDll.h"
#include "jvmti.h"

#include <cstring>

using namespace std;
#ifndef _DEBUG
#define _SECURE_SCL 0
#endif

#define MAXSIZE 15
#define OBSOLETE 99L
#define JVM_ACC_STATIC 0x0008
#define MAX_SIZE 100

typedef struct FieldInfo{
jclass klass;
jfieldID jfID;
int flag;
} FieldInfo;

typedef struct StateInfo
{ 
  char* field_name;
  char* field_sig;
  jfieldID field;
  int flag;
} StateInfo;

struct replace_frame_info
{
 jmethodID method;
};

struct restricted_method_record
{
  jvmtiFrameInfo frame_info;
  char * cur_gname;
  char * cur_file;
  char * similar_gname;
  char * similar_file;
};

typedef struct{
 char* method_name;
 char* method_signature;
} method_info;

typedef struct{
jvmtiFrameInfo frame_info;
char* gname;
} fit_granule;


class finder_method
{
public: 
	finder_method(const string method_name, const string method_sig):m_name(method_name), m_sig(method_sig){}
	bool operator()(const list<method_info>::value_type &value)
	{
	 string para0(value.method_name);
	 string para1(value.method_signature);
	 return para0==m_name && para1==m_sig;	
	}
private:
    string m_name;
	string m_sig;
};
class finder_frame
{
public :
	finder_frame(const jmethodID method) :j_method(method){}
	bool operator()(const list<jmethodID>:: value_type &value)
	{
     return value==j_method;	
	}

private :
	jmethodID j_method;
};

typedef replace_frame_info replace_m_c;

typedef restricted_method_record restricted_method;



class AgentException 
{
 public:
	AgentException(jvmtiError err) {
		m_error = err;
	}

	char* what() const throw() { 
		return "AgentException"; 
	}

	jvmtiError ErrCode() const throw() {
		return m_error;
	}

 private:
	jvmtiError m_error;
};


/*class find_frame
{
public:
	find_frame(const jvmtiFrameInfo f_info, const string g_name) : frame_info(f_info),gname(g_name){}
	bool operator()(const list<fit_granule>::value_type &value)
    {
	  string para(value.gname);	
	  return value.frame_info==frame_info && para==gname;	
	}

private :
	jvmtiFrameInfo frame_info;
	string gname;
};*/



class MethodInvokeAgent 
{
 public:

	MethodInvokeAgent() throw(AgentException){}

	~MethodInvokeAgent() throw(AgentException);

	void Init(JavaVM *vm) const throw(AgentException);
        
	void ParseOptions(const char* str) const throw(AgentException);

	void AddCapability() const throw(AgentException);
        
	void RegisterEvent() const throw(AgentException);

	static void check_jvmti_error(jvmtiEnv *jvmti, jvmtiError error, const char *msg);

	static void enterAgentMonitor(jvmtiEnv *jvmti);

	static void exitAgentMonitor(jvmtiEnv *jvmti);

	static void JNICALL HandleVMInit(jvmtiEnv *jvmti_env, JNIEnv *env, jthread thr);

	static void initMethods(jvmtiEnv *jvmti_env, JNIEnv *env);

	static void JNICALL HandleVMDeath(jvmtiEnv *jvmti_env,JNIEnv* jni_env);

	static void JNICALL HandleThreadEnd(jvmtiEnv *jvmti_env,JNIEnv* jni_env,jthread thread);

	static void printLog(string info,string msg);

 private:
	static void CheckException(jvmtiError error) throw(AgentException)
	{
		if (error != JVMTI_ERROR_NONE) {
			throw AgentException(error);
		}
	}
    
	static jvmtiEnv * m_jvmti;
    static jrawMonitorID m_lock;
	static char* m_filter;
	static vector<string> userclasses;
	static bool isReplace;
//	static vector<string> intercept_m;
	static set<string> methods;
	static set<string> m_names;
	static replace_m_c rep_m_c;
	static int loop_size;
	static map<string,int> c_tab;
	static list<restricted_method> restricted_methods;
    static list<method_info> method_infos;
	static list<jmethodID> method_frames;
	static list<fit_granule> fit_granules;
	static FieldInfo *fInfo;
	static int total_count;
	static StateInfo **st_map;
    static int st_size;
	static int obj_count;
	public:
	static clock_t start;
	static clock_t end;
    static HashTable instances;
	static bool allCheck;
	static char* replace_g_n;
	static string shadowclass;

	static jmethodID getInstance_GVMInit;
	static jmethodID contextTearDown_GVMInit;
	static jclass gvmInit_class;

	static jmethodID getInstance_GranulePolling;
	static jmethodID getPollingResult_GranulePolling;
	static jclass granulePolling_class;

	static jclass invoke_class;
	static jmethodID loadGranule_GranuleLoader;

	static jclass xmlParser_class;
	static jmethodID replaceGranuleTree_XmlParser;
	static jmethodID getLatestShadowClass_XmlParser;
	static jmethodID getFitnessGranuleSet_XmlParser;
	static jmethodID interceptMethods_XmlParser;
	static jmethodID havaShadowClass_XmlParser;

	static jclass granuleTree_class;
	static jmethodID getGranuleAllChildren_granuleTree;
	static jmethodID getGranuleParents_granuleTree;

	static jclass updateInstances_class;
	static jmethodID createNewInstance_UpdateInstances;

	static jobject obj;

	static char* granule_file; 
};
