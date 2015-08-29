//SANTHOSH THANGARAJ
//Student id:800868156, unccid: sthangar@uncc.edu

// Graph.java
// Graph ,vertex and Edge code
// Computes reachable vertex, changes to the graph based on query input, shortest paths,
//print the content of the graph .


import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

// Used to signal violations of preconditions for
//  shortest path algorithms.
class GraphException extends RuntimeException
{
    public GraphException( String name )
    {
        super( name );
    }
}

// Represents a vertex in the graph.
class Vertex implements Comparable<Vertex>
{
    public String     name;   // Vertex name
    
    public Map<String, Edge> adj1;  //adjacent vertices
    public Vertex     prev;   // Previous vertex on shortest path
    public Float        dist;   // Distance of path
    int status;                 //status of vertex
    int color;                 //0-white, 1-grey( white vertex has been discovered),2-black (finished)
    TreeSet<String> listofreachable1; //reachable vertex
    
    public Vertex( String nm )
      {
    	this.name = nm; 
    	adj1 = new TreeMap<String, Edge>( );    
    	listofreachable1 =new TreeSet<String>();
    	reset( ); 
      }

    public void reset( )
      {
    	dist = Float.MAX_VALUE; prev = null; 
      }
    //printpath used to print the path
    public void printpath()
	   {
    	if(this==this.prev)
    	{
    		System.out.print(" "+this.name);
    	}
    	else if(this.prev==null)
    	{
    		System.out.print("unreachable");
    		System.out.print(" "+this.name);
    	}
    	else
    	{
    		this.prev.printpath();
    		System.out.print(" "+this.name);
    	}
	   }
				   
    public int compareTo(Vertex other)
    {
    	return Float.compare(dist, other.dist);
    }

	public Object getkey() {
		// TODO Auto-generated method stub
		return null;
	}      
}

//edge class
class Edge
{
	 String vertexfrom;
	 String vertexto;
	 float distanceft;	
	 int flag; //maintain status of the edge
} 

//graph class
public class Graph {
	
	 public static final Float INFINITY = (float) Integer.MAX_VALUE;
	     static Map<String,Vertex> vertexMap = new TreeMap<String,Vertex>( );	    
	    public static List<Edge> edgeobject=new LinkedList<Edge>();    // list of object for Edge class

	    
	    //Add a new edge to the graph.    
	    public void addEdge( String sourceName, String destName, Edge e1, Edge e2)
	    {
	        Vertex v = getVertex( sourceName );
	        Vertex w = getVertex( destName );     
	        v.adj1.put(destName,e1);
	        w.adj1.put(sourceName, e2);
	    }  

	    /**
	     * If vertexName is not present, add it to vertexMap.
	     * In either case, return the Vertex.
	     */
	    private Vertex getVertex( String vertexName )
	    {
	        Vertex v = vertexMap.get( vertexName );
	        if( v == null )
	        {
	            v = new Vertex( vertexName );
	            vertexMap.put( vertexName, v );
	            v.status=1;
	        }
	        return v;
	    }	   
	    //print function used to print the contents of the graph
	    public static void printfunction()
	    {	    	 
	    	for( Vertex v : vertexMap.values( ) )
	    	{
	    			System.out.println();
	    			if(v.status==0)
	    			{
	    				System.out.print(v.name+" "+"DOWN");
	    			}
	    			if(v.status==1)
	    			{
	    				System.out.print(v.name);
	    			}    		
		    		for (String vertexname: v.adj1.keySet() )
		    		{	    					    				
		    				Edge edge = v.adj1.get(vertexname);
			    			if(edge.flag==1)
			    				{
			    					System.out.println(" ");
			    					System.out.print("    "+edge.vertexto+" "+edge.distanceft);
			    				}
			    			if(edge.flag==0)
		    				{
		    					System.out.println(" ");
		    					System.out.print("    "+edge.vertexto+" "+edge.distanceft+" "+"DOWN");
		    				}
		    		}
	    			    		
	    	}
	    	
	    }
	//clear
	   private void clearAll( )
	    {
	        for( Vertex v : vertexMap.values( ) )
	            v.reset( );
	    }
	   
	 //addedgechange is used to add the edge based on query input
	   public static void addedgechange(String sourcename,String destinationname, Float time)
	   {
		   Edge e=new Edge();
		   int x=1;
		   int count=0;
		   Vertex v =vertexMap.get(sourcename);
		   //check wether it is new vertex
		   if( v == null )
	        {
	            v = new Vertex( sourcename );
	            vertexMap.put( sourcename, v );
	            v.status=1;
	            count++;
	        }
		   Vertex w =vertexMap.get(destinationname);
		   if( w == null )
	        {
	            w = new Vertex( destinationname );
	            vertexMap.put( destinationname, w );
	            w.status=1;
	            count++;
	        }
		   //if new vertex add the edge
		   if(count==1||count==2)
		   {
			   e.vertexfrom=sourcename;
			   e.vertexto=destinationname;
			   e.distanceft=time;
			   e.flag=1;
			   v.adj1.put(destinationname, e);
			   edgeobject.add(e);		
		   }
		   //if no new vertex and no new edge, update time
		   if(count!=1&&count!=2)
		   {
			   for(Vertex v2:vertexMap.values())
			   {
				   for(String w2:v.adj1.keySet())
				   {
					   if(v2.name.equals(sourcename)&&w2.equals(destinationname))
					   {
						   Edge ed1=v2.adj1.get(w2);
						   ed1.distanceft=time;	
						   x=2;
					   }
				   }
			   }
		   }
		 //if no new vertex ,but it is new edge
			   if(count!=1&&count!=2&&x!=2)
			   {
				   Edge e3= new Edge();
				   e3.vertexfrom=sourcename;
				   e3.vertexto=destinationname;
				   e3.distanceft=time;
				   e3.flag=1;
				   v.adj1.put(destinationname, e3);
				   edgeobject.add(e3);		
			   }		      
	   }
	 //deleteedgechange is used to delete the edge based on query input
	   public static void deleteedgechange(String sourcename,String destinationname)
	   {
		   Edge e=new Edge();
		   int count=0;
		   Vertex v =vertexMap.get(sourcename);		  
		   Vertex w =vertexMap.get(destinationname);
		   if( v!=null && w != null )
	        {
			   v.adj1.remove(destinationname);
	            count++;
	        }		   
		   if(count==1)
		   {			  
			   for(int i=0;i<edgeobject.size();i++)
			   {
				   e=edgeobject.get(i);
				   if(e.vertexfrom.equals(sourcename)&&e.vertexto.equals(destinationname))
				   {
					   edgeobject.remove(e);					   
				   }
			   }
		   }		   
	   }
	 //edgestatus is used to update the status of the edge based on query input
	   public static void edgestatus(String queryoperation, String sourcename,String destinationname)
	   {
		   Edge e=new Edge();
			   for(int i=0;i<edgeobject.size();i++)
			   {
				   e=edgeobject.get(i);    
				   
				   if(e.vertexfrom.equals(sourcename)&&e.vertexto.equals(destinationname))
				   {
					   if(queryoperation.equalsIgnoreCase("edgedown"))
					   {
						   e.flag=0;
					   }
					   if(queryoperation.equalsIgnoreCase("edgeup"))
					   {
						   e.flag=1;
					   }
				   }
			   }		   		   
	   }	  
	   //vertexstatus is used to update the status of the vertex based on query input
	   public static void vertexstatus(String queryoperation, String vertexchange)
	   {		   
			   Vertex v = vertexMap.get( vertexchange );
		        if( v != null )
		        {
		        	if(queryoperation.equalsIgnoreCase("vertexdown"))
		        	{
		        		v.status=0;
		        	}
		        	if(queryoperation.equalsIgnoreCase("vertexup"))
		        	{
		        		v.status=1;
		        	}		            
		        }		   		  			  		   		   
	   }	
	   //used to calculate shortet path using Dijsktra algorithm and min heap.
	   public static void shortestdistance(String tailvertex, String headvertex)
			{
				//Get the tail and headvertex
				Vertex source=vertexMap.get(tailvertex);
				MinHeap heap=new MinHeap(vertexMap.size(),source);			
								
				//Initialization
				for(Vertex s:vertexMap.values())	
				{
					s.prev=s==source?source:null;			
					s.dist=(float) (s==source?0.0:Float.MAX_VALUE);						
				}
				
				 List<Vertex> set=new LinkedList<Vertex>();
				 
				 //insert into min heap
				 for(Vertex e: vertexMap.values())
				 {
					 heap.intoheap(e.dist, e);
				 }
				
				 Vertex local2;
				while(heap.size>0)
				{					
					//extract min heap
					  local2=heap.extractminimum();							
						set.add(local2);					
					for(String ver: local2.adj1.keySet())
					{
						Vertex b=vertexMap.get(ver);
						Edge edge3=local2.adj1.get(ver);											
						if(b.dist>local2.dist+edge3.distanceft&&edge3.flag==1&&local2.status==1&&b.status==1)
						{						
							b.dist=local2.dist+edge3.distanceft;
							b.prev=local2;
						}				
					}
					heap.clean();
					for(Vertex e1: vertexMap.values())
					{
						if(!set.contains(e1))
						{
							heap.intoheap(e1.dist, e1);
						}
					}
				}				
			}   
	   
	   //reachable function is used to find all reachable vertices from each vertex
	   //Time complexity is O (V*(|V|)+|E|))
	   // Time complexity  for bfs is (|V|)+|E|) for each vertex.
	   //Therefore the time complexity to run bfs  for v vertex is v times time complexity of bfs.
	   //so the time complexity is v*(|V|)+|E|)
	   public static void reachable()
	   {		   
		   for(Vertex v: vertexMap.values())
		   {
			   //Time complexity is V*(|V|)+|E|). Time complexity  for bfs is (|V|)+|E|) for each vertex.
			   //Therefore the time complexity to run bfs  for v vertex is v times time complexity of bfs.
			   //so the time complexity is v*(|V|)+|E|)
			   if(v.status==1)
			   {
				   bfs(v);
			   }				   			   
		   }
	   }
	   //bfs algorithm to calculate all reachable vertice. Time complexity is (|V|)+|E|)
	   public static void bfs(Vertex s)
	   {
		   //Time complexity is (|V|))
		   for(Vertex v: vertexMap.values())
		   {
			   v.color=0;
		   }
		 //Time complexity is constant time
		   s.color=1;		   
		   
		   PriorityQueue<Vertex> q =new PriorityQueue<Vertex>();		   
		   q.add(s);
		   
		 //Time complexity is (|V|+|E|))
		   while(!q.isEmpty())
		   {
			   Vertex u=q.remove();
			   for(String temp:u.adj1.keySet())
			   {
				   Vertex v=vertexMap.get(temp);
				   Edge ed=u.adj1.get(temp);
				   if(!(temp.equals(s.name))&&v.status==1&&ed.flag==1)
				   {
					   if(v.color==0)
					   {
						   v.color=1;
						   q.add(v);
						   s.listofreachable1.add(temp);
					   }   
				   }				   
			   }
			   u.color=2;			  
		   }
		   
		   //constant time
		   System.out.println();		   
			   System.out.print(s.name);
		   	
			   //printing all valid reachable vertex. time complexity is (no of valid reachable vertex) from an vertex 
		   for(String reachablevertices: s.listofreachable1)
		   {		   
				   System.out.println(" ");
					System.out.print("    "+reachablevertices);		
		   }	
		  
	   }	   
	   
	   //processrequest function is used to read the command/queries and execute corresponding function	    
	    public static boolean processRequest( Scanner in, Graph g )
	    {
	    	String queryoperation=null;
	    	String tailvertex=null;
	    	String headvertex=null;
	    	Float transmit_time=(float) 0.0;
	    	String vertexchange=null;
	        try
	        {
	        	System.out.println();
	        	System.out.print( "Enter query " );
	            String temp = in.nextLine( );
	            StringTokenizer stoken=new StringTokenizer(temp);        
	            
	            if(stoken.countTokens()==1)
	            {
	            	queryoperation=stoken.nextToken();	            	
	            }
	            if(stoken.countTokens()==4)
	            {
	            	String local=null;
	            	queryoperation=stoken.nextToken();
	            	tailvertex=stoken.nextToken();
	            	headvertex=stoken.nextToken();
	            	local=stoken.nextToken();
	            	transmit_time= Float.parseFloat(local);	            	
	            }
	            if(stoken.countTokens()==3)
	            {	            	
	            	queryoperation=stoken.nextToken();
	            	tailvertex=stoken.nextToken();
	            	headvertex=stoken.nextToken();	            	
	            }
	            if(stoken.countTokens()==2)
	            {	            	
	            	queryoperation=stoken.nextToken();
	            	vertexchange=stoken.nextToken();	            	
	            }
	            if(temp.equalsIgnoreCase("print"))
	            {	            	
	            	printfunction();
	            }
	            if(queryoperation.equalsIgnoreCase("addedge"))
	            {
	            	addedgechange(tailvertex,headvertex,transmit_time);
	            }
	            if(queryoperation.equalsIgnoreCase("deleteedge"))
	            {
	            	deleteedgechange(tailvertex,headvertex);
	            }
	            if(queryoperation.equalsIgnoreCase("edgedown"))
	            {
	            	edgestatus(queryoperation, tailvertex,headvertex);
	            }
	            if(queryoperation.equalsIgnoreCase("edgeup"))
	            {
	            	edgestatus(queryoperation, tailvertex,headvertex);
	            }
	            if(queryoperation.equalsIgnoreCase("vertexdown"))
	            {
	            	vertexstatus(queryoperation, vertexchange);
	            }
	            if(queryoperation.equalsIgnoreCase("vertexup"))
	            {
	            	vertexstatus(queryoperation, vertexchange);
	            }
	            if(queryoperation.equalsIgnoreCase("path"))
	            {
	            	shortestdistance( tailvertex,headvertex);
	            	vertexMap.get(headvertex).printpath();
	            	Vertex c=vertexMap.get(headvertex);
	            	System.out.print(" ");
	            	System.out.printf("%4.2f", c.dist);
	            }
	            if(queryoperation.equalsIgnoreCase("reachable"))
	            {
	            	reachable();
	            }
	            if(queryoperation.equalsIgnoreCase("quit"))
	            {
	            	return false;
	            }
	        }
	        catch( NoSuchElementException e )
	          { return false; }
	        catch( GraphException e )
	          { System.err.println( e ); }
	        return true;
	    }

	    //main class. reads from file and create a graph
	public static void main( String [ ] args )
    {
        Graph g = new Graph( );
        try
        {
            FileReader fin = new FileReader( args[1] );
            Scanner graphFile = new Scanner( fin );

            // Read the edges and insert
            String line;
            while( graphFile.hasNextLine( ) )
            {
                line = graphFile.nextLine( );
                StringTokenizer st = new StringTokenizer( line );
                
                Edge ed=new Edge();
        	    Edge ed1=new Edge();
                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Skipping ill-formatted line " + line );
                        continue;
                    }
                    String source  = st.nextToken( );
                    String dest    = st.nextToken( );
                    String distance = st.nextToken();
                    
                    ed.vertexfrom=source;
                    ed.vertexto=dest;
                    ed.distanceft= Float.parseFloat(distance);
                    ed.flag=1;
                    edgeobject.add(ed);    
                    
                    ed1.vertexfrom=dest;
                    ed1.vertexto=source;
                    ed1.distanceft= Float.parseFloat(distance);
                    ed1.flag=1;
                    edgeobject.add(ed1);
                    g.addEdge( source, dest,ed,ed1);
                    
                }
                catch( NumberFormatException e )
                  { System.err.println( "Skipping ill-formatted line " + line ); }
             }
            graphFile.close();
         }
         catch( IOException e )
           { System.err.println( e ); }        

         Scanner in = new Scanner( System.in );
         while( processRequest( in, g ) )
             ;
    }

}
