
public class Hashing {

    public static int hashCode(String s){
        int hash = 0;
        int skip = Math.max(1,s.length() / 3);
        for (int i = 0; i < s.length(); i +=skip){
            hash = (hash*37) + s.charAt(i);
        }
        return hash;
    }

    public static String[] generateCollision(int n){
        String[] results = new String[n];
        
        // Length 6 means skip = 2 (0,2,4 hashed, 1,3,5 ignored)
        int length = 6;

        for (int i =0; i < n; i++){
            char[] chars = new char[length];

            //make skip values identical
            chars[0] = 'A';
            chars[2] = 'A';
            chars[4] = 'A';

            //make non skip values unique
            int temp = i;
            chars[1] = (char) (' ' + (temp % 95));
            temp /=95;
            chars[3] = (char) (' ' + (temp % 95));
            temp /=95;
            chars[5] = (char) (' ' + (temp % 95));

            results[i] = new String(chars);
        }
        
        return results;
    }
  

    public static void main(String[] args) throws Exception{
        String[] hashString1 = generateCollision(50);

        for (String i: hashString1){
            System.out.println("Hash function with string: " + i + " : returns: " + hashCode(i));
        }
    }
}
