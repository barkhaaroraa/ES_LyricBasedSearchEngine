package Jaccard;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class JaccardSimilarity {

    // A reduced list of stop words for lyric-based search engine
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
        "a", "an", "the", "and", "or", "but", "so", "in", "on", "at", "by", "with", "for", "to", "of", "from",
        "i", "me", "you", "he", "she", "it", "we", "they", "us", "them", "am", "is", "are", "was", "were", "be",
        "been", "being", "do", "does", "did", "have", "has", "had", "having", "can", "could", "will", "would", "shall",
        "should", "may", "might", "must", "this", "that", "these", "those", "very", "really", "just", "quite", "too",
        "almost", "always", "never", "now", "then", "here", "there", "when", "how", "like", "than", "because", "up", 
        "down", "out", "into", "through"
    ));

    // Function to calculate Jaccard similarity between two strings
    public static double jaccardSimilarity(String str1, String str2) {
        // Preprocess both strings: Convert to lowercase, remove non-alphanumeric characters, and remove stop words
        str1 = preprocessString(str1);
        str2 = preprocessString(str2);

        // Dynamically determine n for n-grams
        

        // Get the n-grams for both strings
        Set<String> nGrams1 = getNGrams(str1, 2);
        Set<String> nGrams2 = getNGrams(str2, 2);

        // Calculate the intersection and union of both sets
        Set<String> intersection = new HashSet<>(nGrams1);
        intersection.retainAll(nGrams2);  // Get common n-grams

        Set<String> union = new HashSet<>(nGrams1);
        union.addAll(nGrams2);  // Combine both sets

        // Jaccard similarity is the ratio of the intersection to the union
        return (double) intersection.size() / union.size();
    }

    // Function to preprocess the string: convert to lowercase, remove punctuation, and remove stop words
    private static String preprocessString(String str) {
        // Convert to lowercase and remove all non-alphanumeric characters (remove punctuation)
        str = str.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        
        // Split the string into words and filter out stop words
        String[] words = str.split("\\s+");
        StringBuilder processedString = new StringBuilder();
        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                processedString.append(word).append(" ");
                System.out.println(word);
            }
        }
        System.out.println("\n");
        return processedString.toString().trim();
    }

    // Function to generate n-grams from a string
    private static Set<String> getNGrams(String str, int n) {
        Set<String> nGrams = new HashSet<>();
        for (int i = 0; i <= str.length() - n; i++) {
            nGrams.add(str.substring(i, i + n));
        }

        return nGrams;
    }

    public static void main(String[] args) {
        String shortLyrics = "There's a fs bringi I'll the beat";
        String longLyrics = "There's a fire starting in my heart Reaching a fever pitch, and it's bringing me out the dark Finally, I can see you crystal clear Go ahead and sell me out, and then I'll lay your shit bare See how I'll leave with every piece of you Don't underestimate the things that I will do There's a fire starting in my heart Reaching a fever pitch, and it's bringing me out the dark The scars of your love remind me of us They keep me thinkin' that we almost had it all The scars of your love, they leave me breathless I can't help feeling We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) Baby, I have no story to be told But I've heard one on you Now I'm gonna make your head burn Think of me in the depths of your despair Make a home down there As mine sure won't be shared (You're gonna wish you – never had met me) The scars of your love remind me of us(Tears are gonna fall, rolling in the deep) They keep me thinkin' that we almost had it all(You're gonna wish you – never had met me) The scars of your love, they leave me breathless(Tears are gonna fall, rolling in the deep) I can't help feeling We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) Could have had it all Rolling in the deep You had my heart inside of your hand But you played it with a beating Throw your soul through every open door (oh-oh) Count your blessings to find what you look for (whoa-oh) Turn my sorrow into treasured gold (oh-oh) You'll pay me back in kind and reap just what you sow (You're gonna wish you – never had met me) We could have had it all (Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) It all, it all, it all (Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) But you played it, you played it, you played it You played it to the beat";

        double similarity = jaccardSimilarity(shortLyrics, longLyrics);
        System.out.println("Jaccard Similarity: " + similarity);
    }
}
