// Team -- Jessica Yang, Pardeep Singh, Jack Schluger
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
	/*
	OrderedArrayList Franz = new OrderedArrayList();

	System.out.println("\nValues to add via addLinear() calls:");

	// testing linear search
	for( int i = 0; i < 15; i++ ) {
	    int valToAdd = i; //(int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addLinear( valToAdd );
	}

		
	System.out.println("\nafter population via addLinear() calls:");
	System.out.println( Franz );
	System.out.println();

	System.out.println("\nsearch via findLin() calls:");
	for( int i = 0; i < 16; i++ ) {
	    System.out.println( i + ": " + Franz.findLin(i));
	}
*/

	OrderedArrayList Franz = new OrderedArrayList();

	System.out.println("\nGive it a second\n");

	//so we are consistant
	int num = 50000;
       
	// testing binary search
	for( int i = 0; i < num; i++ ) {
	    int valToAdd = (int)( num * Math.random() );
	    Franz.addBinary( valToAdd );
	}
	/*
	System.out.println("\nafter population via addBinary() calls:");
	System.out.println( Franz );
	System.out.println();
        
	
	System.out.println("\nsearch via findBin() calls:");
	for( int i = 0; i < 16; i++ ) {
	    System.out.println( i + ": " + Franz.findBin(i));
	}

	*/
	//avg time for lin search
	double linTimeAvg = 0;

	//# of searches done (so we can take the right average)
	double linTimeCtr = 0;

	//avg time for lin search
	double binTimeAvg = 0;

	//# of searches done (so we can take the right average)
	double binTimeCtr = 0;

	//time before
	double start = 0;
	//time after
	double finish = 0;
	//time during
	double temp = 0;

	//random number, must store it so it is the same for both searches
	int rand = 0;

	for (int i = 0;  i < num; i++){
	    rand = (int)( num * Math.random() );
	    start = System.currentTimeMillis();
	    Franz.findLin(rand);
	    finish = System.currentTimeMillis();
	    
	    temp = finish - start;
	    linTimeAvg = (linTimeAvg * linTimeCtr + temp) / (linTimeCtr + 1) ;
	    linTimeCtr += 1;

	    start = System.currentTimeMillis();
	    Franz.findBin(rand);
	    finish = System.currentTimeMillis();
	    
	    temp = finish - start;
	    binTimeAvg = (binTimeAvg * binTimeCtr + temp) / (binTimeCtr + 1) ;
	    binTimeCtr += 1;
	}
	
	String s = "Average linear search time: ";
	System.out.println(s + linTimeAvg);

	s = "Average binary search time: ";
	System.out.println(s + binTimeAvg);

    }//end main()

}//end class OrderedArrayList
 
