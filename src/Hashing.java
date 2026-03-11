public class Hashing {
    public static String[] generateCollision(int n){
        String[] results = new String[n];

        for (int i = 0; i < n; i++){
            char filler = (char) ('a' + (i % 26));
            char filler2 = (char) ('a' + ((i/26) % 26));

            results[i] = "A" + filler + "A" + filler2 + "A" +"z";
        }
        return results;
    }   

    public static void main(String[] args) throws Exception{
        String[] hashString = generateCollision(5);
        String[] hashString2 = generateCollision(5);
        for (String i: hashString){
            System.out.println(i);
        }

        System.out.println("String 2");

        for (String i: hashString){
            System.out.println(i);
        }
    }
}
