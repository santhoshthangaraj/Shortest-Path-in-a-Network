# Shortest-Path-in-a-Network
Finding the shortest path in a network using min heap data structure and dijsktra algorithm. Finding the all possible reachable host.

Building the Graph:
Your program should run from the command-line:
graph network.txt
where network.txt is a file that contains the initial state of the graph.
Each link representing two directed edges is listed on a line, and is specified by the names of its two vertices followed by the transmission time. Vertices are simply strings (vertex names with no
spaces) and the transmission times are floating point numbers.

Changes to the Graph:
Input about changes to the graph, requests for shortest paths, and requests to print the graph will come as input queries to be read from the standard input. Here are queries indicating changes to
the graph.

addedge tailvertex headvertex transmit time — Add a single directed edge from tailvertex to headvertex. Here headvertex and tailvertex are the names of the vertices and transmit time
is a float specifying the transmission time of the edge. If a vertex does not exist in the graph already, add it to the graph. If the edge is already in the graph, change its transmission
time. Important note: This can enable two vertices to be connected by a directed edge in one direction and not the other, or enable the transmission times in the two directions to be
different.

deleteedge tailvertex headvertex — Delete the specified directed edge from the graph. Do not remove the vertices. If the edge does not exist, do nothing. Note: This may cause two
vertices to be connected by a directed edge in one direction, but not the other. edgedown tailvertex headvertex — Mark the directed edge as “down” and therefore unavailable
for use. The response to an edgedown (or edgeup) query should mark only the specified directed edge as “down” (or “up”). So its companion directed edge (the edge going in the
other direction) should not be affected.

edgeup tailvertex headvertex — Mark the directed edge as “up”, and available for use. Its previous transmission time should be used.
vertexdown vertex — Mark the vertex as “down”. None of its edges can be used. Marking a vertex as “down” should not cause any of its incoming or outgoing edges to be marked
as “down”. So the graph can have “up” edges going to and leaving from a “down” vertex. However a path through such a “down” vertex cannot be used as a valid path.
vertexup vertex — Mark the vertex as “up” again.

Finding the Shortest Path
The query for finding the shortest path will look like
path from_vertex to_vertex
where from_vertex and to_vertex are names of vertices. This should compute the shortest time path from from_vertex to to_vertex using Dijkstra’s algorithm and based on the current state of
the graph.
Implementated of Dijkstra’s algorithm, and especially the priority queue (as a min binary heap). The output must be the vertices along the shortest path, followed by the total transmission time.

Print Graph
The simple query
print
must print the contents of the graph. Vertices must be printed in alphabetical order and the outward edge for each vertex must be printed in alphabetical order
When a vertex is down, append the word DOWN after the vertex. When an edge is down, append the word DOWN after the edge.

Reachable Vertices
Since the states of the vertices and edges may change with time, it is useful to know the set of vertices reachable from each vertex by valid paths. You must develop and implement an algorithm
that identifies for each vertex, the set of all its reachable vertices. 

The query
reachable

must print for each “up” vertex, all vertices that are reachable from it. So vertices that are “down” or not reachable by “up” edges will not be printed. Vertices must be printed in alphabetical order,
and the set of vertices reachable from each vertex must be printed in alphabetical order

Quit
The input query quit should simply cause the program to exit without printing anything
