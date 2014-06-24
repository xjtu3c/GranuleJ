#ifndef __HASHTAB_H__
#define __HASHTAB_H__

#include "jvmti.h"

#include <cstring>

static const size_t HashtableSize = 1013;

class HashTable {
  private:
	struct Entry {
	  Entry* next;
	  jobject new_instance;
	  jobject old_instance;
      unsigned hashCode;
	  bool isStateMigrate;
	};

    Entry** table;
	unsigned _size;
 
  public:
    HashTable() {
        table = new Entry*[HashtableSize];
        memset(table, 0, sizeof(Entry*)*HashtableSize);
    }

	HashTable(unsigned size)
	{
	   _size=size;
	   table=new Entry*[size];
	   memset(table,0,sizeof(Entry*)*size);	
	}

	unsigned Hash(unsigned hashCode){
	  return hashCode % HashtableSize;
	}
    
	void put(jobject old_instance, unsigned hashCode,jobject new_instance){
	    Entry* e = new Entry();
        e->hashCode = hashCode;
        e->old_instance = old_instance;
        e->new_instance = new_instance;
		e->isStateMigrate = false;
        unsigned h=Hash(hashCode);
        e->next = table[h];
        table[h] = e;	
	}

   jobject getKeyObject(unsigned hashCode)
	{
	    unsigned h = Hash(hashCode);
        for (Entry* e = table[h]; e != NULL; e = e->next) {
			if(e->hashCode==hashCode){
                return e->old_instance;
            }
        }
        return NULL;	
	}

  jobject get(jobject old_instance, unsigned hashCode){
	   unsigned h=Hash(hashCode); 
        for (Entry* e = table[h]; e != NULL; e = e->next) {
			if(e->hashCode==hashCode || e->old_instance==old_instance){
                return e->new_instance;
            }
        }
        return NULL;
    }
      
  Entry* getEntry(jobject old_instance, unsigned hashCode)
	{
	    unsigned h = Hash(hashCode);
        for (Entry* e = table[h]; e != NULL; e = e->next) {
			if(e->hashCode==hashCode){
                return e;
            }
        }
        return NULL;
	}

  jobject remove(jobject old_instance,unsigned hashCode){
	    Entry **epp, *ep;
        unsigned h = Hash(hashCode);
        for (epp = &table[h]; (ep = *epp) != NULL; epp = &ep->next) {
			if(ep->hashCode==hashCode){
                *epp = ep->next;
                return ep->new_instance;
            }
        }
        return NULL;
	}
   void clear() {
        for (int i = HashtableSize; --i >= 0;) {
            Entry *e, *next;
            for (e = table[i]; e != NULL; e = next) {
                next = e->next;
                delete e;
            }
            table[i] = NULL;
        }
    }

    ~HashTable() {
        clear();
        delete[] table;
    }
};

#endif
