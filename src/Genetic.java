import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

class Genetic
{
    public static void main(String[] args)
    {
        //fitness("sjoerd", "sjoert");
        //generate(10,"sjoerd");
        //fitpop(generate(10,"sjoerd"),"sjoerd");
        //selected(generate(1000,"sjoerd"), 10, "sjoerd");
        //makechild("sjoerd","wietze");
        //offspring(selected(generate(1000,"sjoerd"), 10, "sjoerd"));
        //mutate(makechild("sjoerd","wietze"));
        mutatepop(offspring(selected(generate(1000,"testword"), 10, "testword")), 10);
    }

    public static float fitness(String password, String guess)
    {
        int l = password.length();
        int l2 = guess.length();
        float score = 0.0f;
        if(l != l2){
            System.out.println("length differs");
        } else {
            int i = 0;
            while(i < l){
                if(password.charAt(i) == guess.charAt(i)){
                    score += 1;
                }
                i++;
            }
        }

        //System.out.println(score / l);
        return score / l;
    }

    public static String[] generate(int n, String password)
    {
        String[] words = new String[n];
        int l = password.length();
        Random random = new Random();
        for(int i = 0; i < n; i++)
        {
            char[] word = new char[l]; //words of length password.length
            for(int j = 0; j < l; j++)
            {
                word[j] = (char)(random.nextInt(26) + 'a');
            }
            words[i] = new String(word);
        }
        //System.out.println(Arrays.toString(words));
        return words;
    }

    public static float[] fitpop(String[] words, String password)
    {
        int l = words.length - 1;
        float[] fit = new float[l + 1];
        for(int i = 0; i < l; i++)
        {
            fit[i] = fitness(words[i] ,password);
        }
        for(int j = 0; j < l; j++)
        {
            //System.out.print(fit[j]);
        }
        return fit;
    }

    public static ArrayList<String> selected(String[] words, int lucky_few, String password)
    {
        ArrayList<String> selected = new ArrayList<String>();
        int l = words.length - 1;
        Random random = new Random();
        for(int i = 0; i < l; i++)
        {
            int rand = random.nextInt(100);
            if(rand < (fitness(words[i], password)) * 100)
            {
                selected.add(words[i]);
            } else if(rand < lucky_few) {
                selected.add(words[i]);
            }
        }
        //System.out.print(Arrays.toString(selected.toArray()));
        //System.out.println();
        System.out.println("number of selected genes = " + selected.size());
        return selected;
    }

    public static char[] makechild(String gene1, String gene2)
    {
        int l = gene1.length();
        char[] child = new char[l];
        Random random = new Random();
        for(int i = 0; i < l; i++)
        {
            int rand = random.nextInt(100);
            if(rand < 50)
            {
                child[i] = gene1.charAt(i);
            } else {
                child[i] = gene2.charAt(i);
            }
        }
        //System.out.println(child);
        return child;
    }

    public static ArrayList<char[]> offspring(ArrayList<String> words)
    {
        ArrayList<char[]> offspring = new ArrayList<char[]>();
        int l = words.size();
        for(int i = 0; i < l; i++)
        {
            offspring.add(makechild(words.get(i),words.get(l - 1)));
        }
        for(int i = 0; i < offspring.size(); i++)
        {
            System.out.println(offspring.get(i));
        }
        System.out.println();
        System.out.println("number of children = " + offspring.size());
        return(offspring);
    }


    public static char[] mutate(char[] child) //can only mutate childs.
    {
        int l = child.length;
        Random random = new Random();
        for(int i = 0; i < l; i++)
        {
            int rand = random.nextInt(100);
            if(rand < 51)
            {
                child[i] = (char)(random.nextInt(26) + 'a');
            }
        }
        System.out.println(child);
        return child;
    }

    public static ArrayList<char[]> mutatepop(ArrayList<char[]> offspring, int p) //p is between 1-100.
    {
        ArrayList<char[]> mutatepop = new ArrayList<char[]>();
        int l = offspring.size();
        Random random = new Random();
        for(int i = 0; i < l; i++)
        {
            int rand = random.nextInt(100);
            if(rand < p)
            {
                mutatepop.add(mutate(offspring.get(i)));
            } else {
                mutatepop.add(offspring.get(i));
            }
        }
        for(int i = 0; i < mutatepop.size(); i++)
        {
            System.out.println(mutatepop.get(i));
        }
        System.out.println();
        System.out.println("number of childs after mutation = " + mutatepop.size());
        return mutatepop;
    }

}
