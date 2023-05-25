
heuristic: cost = 3.396931, 6.884000 milliseconds
backtrack: cost = 1.356678, 30126.692000 milliseconds
mine: cost = 1.356678, 201.777000 milliseconds


Heuristic performs the best because it has almost linear complexity since it chooses the lowest cost edge and goes to the next node
This leads to it being the fastest since backtrack needs to check every node and path

Backtrack performs the worst because it has to check every possible path, which can get very expensive. 
The cost is the best since it checks everypath, but with larger graphs, it can take a very long time compared to the other algorithms

Mine performs well because it takes the backtrack method and removes paths that are not going to be able to beat the current lowest cost
Simply, it compares the current cost to the best and exclues the ones that are already greater.