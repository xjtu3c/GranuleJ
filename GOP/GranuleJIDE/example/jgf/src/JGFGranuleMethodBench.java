

//benchmark for  granule instance method invocation that is invoked by granule method in the same granule.
granule g_1(JGFMethodBench){
		return{true;}
		
        class JGFMethodBench{
            public void instance_method(){k++;}
            public void JGFrun(){

    int i,size;
    double time; 

    
    // Test non empty instance method (increments a variable)
    JGFInstrumentor.addTimer("Section1:Method:Same:Instance", "calls");      time = 0.0; 
    size = INITSIZE; 
    while (time < TARGETTIME && size < MAXSIZE){
      JGFInstrumentor.resetTimer("Section1:Method:Same:Instance"); 
      JGFInstrumentor.startTimer("Section1:Method:Same:Instance");       
      for (i=0; i<size; i++){
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
			instance_method();
      }
      JGFInstrumentor.stopTimer("Section1:Method:Same:Instance");      
      time = JGFInstrumentor.readTimer("Section1:Method:Same:Instance");
      JGFInstrumentor.addOpsToTimer("Section1:Method:Same:Instance", (double) 16*size);
      size *=2; 
    }    
    JGFInstrumentor.printperfTimer("Section1:Method:Same:Instance");
}
            
        }			
}

// benchmark for granule (synchronized) class method invocation
granule g(JGFMethodBench){
		return{true;}
		class JGFMethodBench{
		       public void instance_method(){k++;}
               public static void class_method() { k++;} 
               public final void final_instance_method(){k++;}
               synchronized public static void synch_class_method() {k++;}
               synchronized public void synch_instance_method(){k++;}
  }
  }
  
  
 granule g2(JGFMethodBench){
		return{true;}
        class MethodTester {

        static int k = 0; 
        public void instance_method(){k++;}
        public static void class_method() {k++;}
    }
}  

 granule g3(MethodTester2){
 		return{true;}
        class MethodTester2{
             public void instance_method(){k++;}
        }
}
