import java.util.*;
public class Question2 {
    //String ram is for storing ans of ram, sham is for storing ans of sham, similarly rahim
    static String ram = "";
    static String sham = "";
    static String rahim = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> apples = new ArrayList<>();
        System.out.println("run distribute_apple");

        //keep on getting weight from user until he enters -1
        while (true) {
            System.out.print("Enter apple weight in grams (-1 to stop): ");
            int weight = sc.nextInt();
            if (weight == -1) break;
            apples.add(weight);
        }

        //visited array to maintain weights, eliminate duplicates
        boolean vis[] = new boolean[apples.size()];
        int totalWeight = getTotalWeight(apples);
        ram = "";
        sham = "";
        rahim = "";
        int requiredWtForRam = totalWeight * 50 / 100;
        //distribute the requiredWeight apple to ram and also send totalWeight for sending the percentage of weight further
        //check if distribution can be done or not
        if (!shareForRam(apples, vis, 0, requiredWtForRam, totalWeight, "")) {
            System.out.println("Distribution Result: Not Enough Number of apples so their weights can't be cut in given percentage!");
            return;
        }
        //Distribution can be done
        System.out.println("");
        System.out.println("Distribution Result:");
        //static values of ram, sham and rahim might have got the respective correct values
        //remove last ", " then print them
        if (!ram.isEmpty() && !sham.isEmpty() && !rahim.isEmpty()) {
            System.out.println("Ram: " + ram.substring(0, ram.length() - 2));
            System.out.println("Sham: " + sham.substring(0, sham.length() - 2));
            System.out.println("Rahim: " + rahim.substring(0, rahim.length() - 2));
        }
    }

    static boolean shareForRam(List<Integer> apples, boolean vis[], int idx, int weightForRam, int totalWeight, String ans) {
        //if ram's weight is 0, now check with this visited array only that are others able to make their weights 0 or not(Distrubution possible or not)
        if (weightForRam == 0) {
            int weightForSham = totalWeight * 30 / 100;
            //check if sham can take weights, if he can then update the value of minlength only and return true(means distribution was done)
            if (shareForSham(apples, vis, 0, weightForSham, totalWeight, "")) {
                if(ram.isEmpty() || ram.length() > ans.length()) ram = ans;
                return true;
            }
            return false;//not distributed
        }

        if (weightForRam < 0 || idx == apples.size()) return false;

        boolean picked = false;
        boolean notPicked = false;
        //if not already visited then only visit it and pick it
        if (!vis[idx]) {
            vis[idx] = true;
            picked = shareForRam(apples, vis, idx + 1, weightForRam - apples.get(idx), totalWeight, ans + apples.get(idx) + ", ");
            vis[idx] = false;
        }
        notPicked = shareForRam(apples, vis, idx + 1, weightForRam, totalWeight, ans);
        //whether got distributed in picking up value, or in not picking up, return true if anyone is true
        return picked || notPicked;
    }
    static boolean shareForSham(List<Integer> apples, boolean vis[], int idx, int weightForSham, int totalWeight, String ans) {
        //if sham's weight is 0, now check with this visited array only that is rahim req. weight can be made or not(Distrubution possible or not)
        if (weightForSham == 0) {
            int weightForRahim = totalWeight * 20 / 100;
            //check if rahim can take weight, if he can then update the value with minlength of ans only and return true(means distribution was done)
            if (shareForRahim(apples, vis, 0, weightForRahim, "")) {
                if(sham.isEmpty() || sham.length() > ans.length()) sham = ans;
                return true;
            }
            return false;
        }

        if (weightForSham < 0 || idx == apples.size()) return false;

        boolean picked = false;
        boolean notPicked = false;
        //if not already visited then only visit it and pick it
        if (!vis[idx]) {
            vis[idx] = true;
            picked = shareForSham(apples, vis, idx + 1, weightForSham - apples.get(idx), totalWeight, ans + apples.get(idx) + ", ");
            vis[idx] = false;
        }

        notPicked = shareForSham(apples, vis, idx + 1, weightForSham, totalWeight, ans);
        //whether got distributed in picking up value, or in not picking up, return true if anyone is true
        return picked || notPicked;
    }

    static boolean shareForRahim(List<Integer> apples, boolean vis[], int idx, int weightForRahim, String ans) {
        if (weightForRahim == 0) {
            //if weight has become 0, but also check if there was a last index, if yes then it shouldn't be visited...then update only
            if(idx == vis.length) rahim = ans;
            else if(idx != 0){
                if(!vis[idx - 1]) rahim = ans;
            }
            return true;
        }

        if (weightForRahim < 0 || idx == apples.size()) return false;

        boolean picked = false;
        boolean notPicked = false;
        //if not already visited then only visit it and pick it
        if (!vis[idx]) {
            vis[idx] = true;
            picked = shareForRahim(apples, vis, idx + 1, weightForRahim - apples.get(idx),  apples.get(idx) + ", " + ans);
            vis[idx] = false;
        }
        notPicked = shareForRahim(apples, vis, idx + 1, weightForRahim, ans);
        return picked || notPicked;
    }

    private static int getTotalWeight(List<Integer> list) {
        int total = 0;
        for (int weight : list) {
            total += weight;
        }
        return total;
    }
}
