import java.util.*;
// NOTE : Some Steps are getting repeated while printing, I appologize for that actually I tried fixing it but couldn't do so and time was running out as well
public class Question3 {
    public static void helper(int castleRow, int castleCol, char currDir, List<String> currPath, int currR, int currC, int[][] grid, List<List<String>> allPaths) {
        // Base case: If we reach the castle
        if (currR == castleRow && currC == castleCol && currPath.size() > 1) {
            currPath.add("Arrive (" + castleCol + "," + castleRow + ")");
            allPaths.add(new ArrayList<>(currPath));
            return;
        }
        // Invalid coordinate
        if (currR < 0 || currR >= grid.length || currC < 0 || currC >= grid[0].length) {
            return;
        }
        // Check if the current position is a soldier
        if (grid[currR][currC] == 0) { // Soldier region
            // Determine next direction and position
            if (currDir == 'D') {
                // Kill soldier and then jump over it
                grid[currR][currC] = 1; // Mark the soldier as killed
                currPath.add("Kill (" + currC + "," + currR + ") Turn Left");
                helper(castleRow, castleCol, 'R', currPath, currR, currC + 1, grid, allPaths);
                currPath.remove(currPath.size() - 1);

                grid[currR][currC] = 0; // Backtracking
                currPath.add("Jump (" + currC + "," + currR + ")");
                helper(castleRow, castleCol, 'D', currPath, currR + 1, currC, grid, allPaths);
                currPath.remove(currPath.size() - 1);
            } else if (currDir == 'R') {
                grid[currR][currC] = 1;
                currPath.add("Kill (" + currC + "," + currR + ") Turn Left");
                helper(castleRow, castleCol, 'U', currPath, currR - 1, currC, grid, allPaths);
                currPath.remove(currPath.size() - 1);

                grid[currR][currC] = 0; // Backtracking
                currPath.add("Jump (" + currC + "," + currR + ")");
                helper(castleRow, castleCol, 'R', currPath, currR, currC + 1, grid, allPaths);
                currPath.remove(currPath.size() - 1);
            } else if (currDir == 'U') {
                grid[currR][currC] = 1;
                currPath.add("Kill (" + currC + "," + currR + ") Turn Left");
                helper(castleRow, castleCol, 'L', currPath, currR, currC - 1, grid, allPaths);
                currPath.remove(currPath.size() - 1);

                grid[currR][currC] = 0; // Backtracking
                currPath.add("Jump (" + currC + "," + currR + ")");
                helper(castleRow, castleCol, 'U', currPath, currR - 1, currC, grid, allPaths);
                currPath.remove(currPath.size() - 1);
            } else { // currDir == 'L'
                grid[currR][currC] = 1;
                currPath.add("Kill (" + currC + "," + currR + ") Turn Left");
                helper(castleRow, castleCol, 'D', currPath, currR + 1, currC, grid, allPaths);
                currPath.remove(currPath.size() - 1);

                grid[currR][currC] = 0; // Backtracking
                currPath.add("Jump (" + currC + "," + currR + ")");
                helper(castleRow, castleCol, 'L', currPath, currR, currC - 1, grid, allPaths);
                currPath.remove(currPath.size() - 1);
            }


        } else { // Regular move
            if (currDir == 'D') {
                helper(castleRow, castleCol, 'D', currPath, currR + 1, currC, grid, allPaths);
            } else if (currDir == 'R') {
                helper(castleRow, castleCol, 'R', currPath, currR, currC + 1, grid, allPaths);
            } else if (currDir == 'U') {
                helper(castleRow, castleCol, 'U', currPath, currR - 1, currC, grid, allPaths);
            } else {
                helper(castleRow, castleCol, 'L', currPath, currR, currC - 1, grid, allPaths);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of soldiers: ");
        int soldierCount = sc.nextInt();
        int[][] soldiers = new int[soldierCount][2];
        int maxRow = 0, maxCol = 0;
        sc.nextLine();

        for (int i = 0; i < soldierCount; i++) {
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            String s = sc.nextLine();
            int col = s.charAt(0) - '0';
            int row = s.charAt(s.length() - 1) - '0';
            soldiers[i][0] = col;
            soldiers[i][1] = row;
            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }
        System.out.print("Enter the coordinates for your 'special' castle: ");
        String specialCastle = sc.nextLine();
        int castleCol = specialCastle.charAt(0) - '0';
        int castleRow = specialCastle.charAt(specialCastle.length() - 1) - '0';

        int[][] grid = new int[maxRow + 1][maxCol + 1];
        for (int[] g : grid) {
            Arrays.fill(g, 1);
        }

        for (int[] s : soldiers) {
            grid[s[1]][s[0]] = 0;
        }

        grid[castleRow][castleCol] = 2;

        List<List<String>> allPaths = new ArrayList<>();
        List<String> currPath = new ArrayList<>();
        currPath.add("Start (" + 1 + "," + 2 + ")");
        helper(castleRow, castleCol, 'D', currPath, 2, 1, grid, allPaths);

        // Print all paths
        System.out.println("All possible paths:");
        int pathNum = 1;
        for(int i=allPaths.size() - 1; i>=0; i--){
            System.out.println("Path " + pathNum + "\n===========");
            for (String step : allPaths.get(i)) {
                System.out.println(step);
            }
            System.out.println();
            pathNum++;
        }
    }
}
