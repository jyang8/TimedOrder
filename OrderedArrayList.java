// Team Pittsburgh-- Jessica Yang, Pardeep Singh, Jack Schluger
// APCS1 pd9
// HW48 -- Halfling Time Trials
// 2015-12-14

/*============================================
  class OrderedArrayList
  Wrapper class for ArrayList.
  Imposes the restriction that stored items 
  must remain sorted in ascending order
  ============================================*/

//ArrayList's implementation is in the java.util package
import java.util.ArrayList;


public class OrderedArrayList {

    // instance of class ArrayList, holding objects of type Comparable 
    // (ie, instances of a class that implements interface Comparable)
    private ArrayList<Comparable> _data;


    // default constructor initializes instance variable _data
    public OrderedArrayList() {
	_data = new ArrayList<Comparable>();
    }


    public String toString() { 
	return _data.toString(); 
    }


    public Comparable remove( int index ) { 
	return _data.remove(index); 
    }


    public int size() { 
	return _data.size();
    }

    
    public Comparable get( int index ) { 
	return _data.get(index); 
    }


    // addLinear takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a linear search to find appropriate index
    public void addLinear( Comparable newVal ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {
	    if ( newVal.compareTo( _data.get(p) ) < 0 ) { //newVal < oal[p]
		_data.add( p, newVal );
		return; //Q:why not break?
	    }
	}
	_data.add( newVal ); //newVal > every item in oal, so add to end
    }


    // addBinary takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a binary search to find appropriate index
    public void addBinary( Comparable newVal ) { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while ( lo <= hi ) { //running until target is found or bounds cross

	    med = (lo + hi) / 2;
	    int x = _data.get(med).compareTo( newVal );
	        
	    if ( x == 0 ) { //equal value found, insert here
		_data.add( med, newVal );
		return;
	    }
	    else if ( x > 0 ) //newVal < med, so look at lower half of data
		hi = med - 1;
	    else //newVal > med, so look at upper half of data
		lo = med + 1;
	}
	// If you make it this far, newVal was not in the ArrayList.
	// So insert at lo. Q: How do you know lo is correct insertion index?
	_data.add( lo, newVal );
    }	


    // determine whether element present in data structure using linear search
    // return index of occurrence or -1 if not found
    public int findLin( Comparable target ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {
	    if ( target.compareTo( _data.get(p) ) == 0 ) { //newVal < oal[p]
		return p;
	    }
	}
	return -1;
	}


    // determine whether element present in data structure using binary search
    // return index of occurrence or -1 if not found
    public int findBin( Comparable target ) 
    { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while ( lo <= hi ) { //running until target is found or bounds cross

	    med = (lo + hi) / 2;
	    int x = _data.get(med).compareTo( target );
	        
	    if ( x == 0 ) { //equal value found, insert here
		return med;
	    }
	    else if ( x > 0 ) //target < med, so look at lower half of data
		hi = med - 1;
	    else //target > med, so look at upper half of data
		lo = med + 1;
	}
	// If you make it this far, target was not in the ArrayList.
	// So insert at lo. Q: How do you know lo is correct insertion index?
	return -1;
	}


    // main method solely for testing purposes
    public static void main( String[] args ) 
    {
	OrderedArrayList Franz = new OrderedArrayList();
	/*
	//	System.out.println("\nValues to add via addLinear() calls:");

	// testing linear search
	for( int i = 0; i < 100000001; i++ ) {
	    int valToAdd = i; //(int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addLinear( valToAdd );
	}

		
	System.out.println("\nafter population via addLinear() calls:");
	System.out.println( Franz );
	System.out.println();

	System.out.println("\nsearch via findLin() calls:");
	for( int i = 0; i < 20; i++ ) {
	    System.out.println( i + ": " + Franz.findLin(i));
	}
	
	//	Franz = new OrderedArrayList();

	//System.out.println("\nValues to add via addBinary() calls:");

	*/			
	// testing binary search
	for( int i = 0; i < 100001; i++ ) {
	    int valToAdd = i; //(int)( 50 * Math.random() );
	    //  System.out.println( valToAdd );
	    Franz.addBinary( valToAdd );
	}
	/*
	System.out.println("\nafter population via addBinary() calls:");
	System.out.println( Franz );
	System.out.println();
        

       	System.out.println("\nsearch via findBin() calls:");
	for( int i = 0; i < 20; i++ ) {
	    System.out.println( i + ": " + Franz.findBin(i));
	}
	*/
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   INSERT WELL-COMMENT TIMING APPARATUS HERE
	   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


	//findLin() First Number
	System.out.println("\nTime for First number using findLin() calls:");
	double totalTime = 0;//creates a variable to hold the total time of all the trials
	for (int x = 0; x < 10001; x++) {//runs 100000 trials
	    double time = System.nanoTime();//sets starting time
	    Franz.findLin(0);//runs findLin()
	    totalTime += (System.nanoTime() - time);//finds the difference in current time and start time and adds to total time
	}
	System.out.println( "Total: " + totalTime + " nanoseconds");//prints total time
	System.out.println( "Average: " + (totalTime /100000.0) + " nanoseconds");//prints average time per trial

	//findLin() Last Number
	System.out.println("\nTime for Middle number using findLin() calls:");
	totalTime = 0;//resets time
	for (int x = 0; x < 100001; x++) {//runs 100000 trials
	    double time = System.nanoTime();//sets starting time
	    Franz.findLin(50000);//runs findLin()
	    totalTime += (System.nanoTime() - time);//finds the difference in current time and start time and adds to total time
	}
	System.out.println( "Total: " + totalTime + " nanoseconds" );//prints total time
	System.out.println( "Average: " + (totalTime /100000.0) + " nanoseconds");//prints average time per trial

	//findLin() Last Number
	System.out.println("\nTime for Last number using findLin() calls:");
	totalTime = 0;//resets time
	for (int x = 0; x < 100001; x++) {//runs 100000 trials
	    double time = System.nanoTime();//sets starting time
	    Franz.findLin(100000);//runs findLin()
	    totalTime += (System.nanoTime() - time);//finds the difference in current time and start time and adds to total time
	}
	System.out.println( "Total: " + totalTime + " nanoseconds" );//prints total time
	System.out.println( "Average: " + (totalTime /100000.0) + " nanoseconds");//prints average time per trial

	//findBin() First Number
	System.out.println("\nTime for First number using findBin() calls:");
	totalTime = 0;//resests time
	for (int x = 0; x < 100001; x++) {//runs 100000 trials
	    double time = System.nanoTime();//sets starting time
	    Franz.findBin(0);//runs findBin()
	    totalTime += (System.nanoTime() - time);//finds the difference in current time and start time and adds to total time
	}
	System.out.println( "Total: " + totalTime + " nanoseconds" );//prints total time
	System.out.println( "Average: " + (totalTime /100000.0) + " nanoseconds");//prints average time per trial

	//findBin() Middle Number
	System.out.println("\nTime for Middle number using findBin() calls:");
	totalTime = 0;//resets time
	for (int x = 0; x < 100001; x++) {//runs 100000 trials
	    double time = System.nanoTime();//sets starting time
	    Franz.findBin(50000);//runs findBin()
	    totalTime += (System.nanoTime() - time);//finds the difference in current time and start time and adds to total time
	}
	System.out.println( "Total: " + totalTime + " nanoseconds" );//prints total time
	System.out.println( "Average: " + (totalTime /100000.0) + " nanoseconds");//prints average time per trial

	//findBin() Last Number
	System.out.println("\nTime for Last number using findBin() calls:");
	totalTime = 0;//resets time
	for (int x = 0; x < 100001; x++) {//runs 100000 trials
	    double time = System.nanoTime();//sets starting time
	    Franz.findBin(100000);//runs findBin()
	    totalTime += (System.nanoTime() - time);//finds the difference in current time and start time and adds to total time
	}
	System.out.println( "Total: " + totalTime  + " nanoseconds");//prints total time
	System.out.println( "Average: " + (totalTime /100000.0) + " nanoseconds");//prints average time per trial
    }

}//end class OrderedArrayList
