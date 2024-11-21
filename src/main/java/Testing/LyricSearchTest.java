package Testing;

import Main.TestingMain;
import co.elastic.clients.elasticsearch.ElasticsearchClient;

public class LyricSearchTest {

    public static void main(String[] args) {
        String serverUrl = "http://localhost:9200";
        String apiKey = "aVlNRVRwTUIwSlEwVFg0OXUzc3c6NzJTRmpjRlVRZ1NWbF9PSk5KT2k2Zw=="; 
        ElasticsearchClient esClient = TestingMain.initializeClient(serverUrl, apiKey);

        if (esClient == null) {
            System.err.println("Failed to initialize Elasticsearch client.");
            return;
        }

        String[][] testCases = {
            {"hello from the other side", "Hello"},
            {"There's a fire starting in my heart Reaching a fever pitch, and it's bringing me out the dark Finally, I can see you crystal clear Go ahead and sell me out, and then I'll lay your shit bare See how I'll leave with every piece of you Don't underestimate the things that I will do There's a fire starting in my heart Reaching a fever pitch, and it's bringing me out the dark The scars of your love remind me of us They keep me thinkin' that we almost had it all The scars of your love, they leave me breathless I can't help feeling We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) Baby, I have no story to be told But I've heard one on you Now I'm gonna make your head burn Think of me in the depths of your despair Make a home down there As mine sure won't be shared (You're gonna wish you – never had met me) The scars of your love remind me of us(Tears are gonna fall, rolling in the deep) They keep me thinkin' that we almost had it all(You're gonna wish you – never had met me) The scars of your love, they leave me breathless(Tears are gonna fall, rolling in the deep) I can't help feeling We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) Could have had it all Rolling in the deep You had my heart inside of your hand But you played it with a beating Throw your soul through every open door (oh-oh) Count your blessings to find what you look for (whoa-oh) Turn my sorrow into treasured gold (oh-oh) You'll pay me back in kind and reap just what you sow (You're gonna wish you – never had met me) We could have had it all (Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) It all, it all, it all (Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) But you played it, you played it, you played it You played it to the beat", "Rolling in the Deep"},
            {"There's a fire starting in my heart hings that I will do There's a fire starting in my heart Reaching a fever pitch, and it's bringing me out the dark The scars of your love remind me of us They keep me thinkin' that we almost had it all The scars of your love, they leave me breathless I can't help feeling We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) Baby, I have no story to be told But I've heard one on you Now I'm gonna make your head burn Think of me in the depths of your despair Make a home down there As mine sure won't be shared (You're gonna wish you – never had met me) The scars of your love remind me of us(Tears are gonna fall, rolling in the deep) They keep me thinkin' that we almost had it all(You're gonna wish you – never had met me) The scars of your love, they leave me breathless(Tears are gonna fall, rolling in the deep) I can't help feeling We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) Could have had it all Rolling in the deep You had my heart inside of your hand But you played it with a beating Throw your soul through every open door (oh-oh) Count your blessings to find what you look for (whoa-oh) Turn my sorrow into treasured gold (oh-oh) You'll pay me back in kind and reap just what you sow (You're gonna wish you – never had met me) We could have had it all (Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) It all, it all, it all (Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) And you played it to the beat(Tears are gonna fall, rolling in the deep) We could have had it all(You're gonna wish you – never had met me) Rolling in the deep (Tears are gonna fall, rolling in the deep) You had my heart inside of your hand(You're gonna wish you – never had met me) But you played it, you played it, you played it You played it to the beat", "Rolling in the Deep"},
            {"There's a fire starting in my heart Reaching a fever pitch", "Rolling in the Deep"},
            {"There's a fire", "Rolling in the Deep"},
            {"We were staying in Paris To get away from your parents And I thought, “Wow If I could take this in a shot right now I don't think that we could work this out” Out on the terrace I don't know if it's fair but I thought “How could I let you fall by yourself While I'm wasted with someone else?” If we go down then we go down together They'll say you could do anything They'll say that I was clever If we go down then we go down together We'll get away with everything Let's show them we are better Let's show them we are better Let's show them we are better We were staying in Paris To get away from your parents You look so proud Standing there with a frown and a cigarette Posting pictures of yourself on the Internet Out on the terrace We breathe in the air of this small town on our own, cuttin' class for the thrill of it Getting drunk on the past, we were livin' in If we go down then we go down together They'll say you could do anything They'll say that I was clever If we go down then we go down together We'll get away with everything Let's show them we are better Let's show them we are Show them we are Let's show them we are Show them we are Let's show them we are better We were staying in Paris Let's show them we are better Let's show them we are Show them we are Let's show them we are Show them we are Let's show them we are better If we go down then we go down together They'll say you could do anything They'll say that I was clever If we go down then we go down together We'll get away with everything Let's show them we are better We were staying in Paris (If we go down) We were staying in Paris (If we go down) We were staying in Paris (If we go down) We were staying in Paris (If we go down) Let's show them we are better We were staying in Paris Let's show them we are better Let's show them we are better If we go down Let's show them we are better If we go down Let's show them we are better If we go down Let's show them we are better Let's show them we are better", "Paris"},
            {"We were I could take this in a shot right now I the terrace I don't know if it's fair but I","Paris"},
            {"Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma Roma-ma Gaga, Oh la-la Want your bad romance Ra-ra-ah-ah-ah Roma, Roma-ma Gaga, Oh la-la Want your bad romance I want your ugly, I want your disease I want your everything as long as it's free I want your love Love, love, love, I want your love, hey! I want your drama, the touch of your hand, (Hey!) I want your leather-studded kiss in the sand I want your love Love, love, love, I want your love (Love, love, love, I want your love) You know that I want you And you know that I need you I want it bad Your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, Oh la-la Want your bad romance I want your horror, I want your design ‘Cause you're a criminal as long as you're mine I want your love Love, love, love, I want your love, huh I want your psycho, your vertigo shtick (Hey, hey!) Want you in my rear window, baby, you're sick I want your love Love, love, love, I want your love (Love, love, love, I want your love) You know that I want you And you know that I need you ('cause I'm a free bitch, baby) I want it bad Your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, Oh la-la Want your bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, Oh la-la Want your bad romance Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, passion, baby Work it, I'm a free bitch, baby I want your love, and I want your revenge I want your love, I don't wanna be friends Je veux ton amour et je veux ta revanche Je veux ton amour, I don't wanna be friends (Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh) No, I don't wanna be friends (Want your bad romance) I don't wanna be friends Want your bad romance Want your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh (Want your bad romance) Caught in a bad romance (Want your bad romance) Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh (Want your bad romance) Caught in a bad romance Ra-ra-ah-ah-ah Roma, Roma-ma Gaga, \"Oh la-la\" Want your bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma Roma-ma Gaga, Oh la-la Want your bad romance Ra-ra-ah-ah-ah Roma, Roma-ma Gaga, Oh la-la Want your bad romance I want your ugly, I want your disease I want your everything as long as it's free I want your love Love, love, love, I want your love, hey! I want your drama, the touch of your hand, (Hey!) I want your leather-studded kiss in the sand I want your love Love, love, love, I want your love (Love, love, love, I want your love) You know that I want you And you know that I need you I want it bad Your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, Oh la-la Want your bad romance I want your horror, I want your design Cause you're a criminal as long as you're mine I want your love Love, love, love, I want your love, huh I want your psycho, your vertigo shtick (Hey, hey!) Want you in my rear window, baby, you're sick I want your love Love, love, love, I want your love (Love, love, love, I want your love) You know that I want you And you know that I need you ('cause I'm a free bitch, baby) I want it bad Your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, \"Oh la-la\" Want your bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, \"Oh la-la\" Want your bad romance Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, passion, baby Work it, I'm a free bitch, baby I want your love, and I want your revenge I want your love, I don't wanna be friends Je veux ton amour et je veux ta revanche Je veux ton amour, I don't wanna be friends (Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh) No, I don't wanna be friends (Want your bad romance) I don't wanna be friends Want your bad romance Want your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh (Want your bad romance) Caught in a bad romance (Want your bad romance) Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh (Want your bad romance) Caught in a bad romance Ra-ra-ah-ah-ah Roma, Roma-ma Gaga, \"Oh la-la\" Want your bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma Roma-ma Gaga, \"Oh la-la\" Want your bad romance Ra-ra-ah-ah-ah Roma, Roma-ma Gaga, Oh la-la Want your bad romance I want your ugly, I want your disease I want your everything as long as it's free I want your love Love, love, love, I want your love, hey! I want your drama, the touch of your hand, (Hey!) I want your leather-studded kiss in the sand I want your love Love, love, love, I want your love (Love, love, love, I want your love) You know that I want you And you know that I need you I want it bad Your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, \"Oh la-la\" Want your bad romance I want your horror, I want your design ‘Cause you're a criminal as long as you're mine I want your love Love, love, love, I want your love, huh I want your psycho, your vertigo shtick (Hey, hey!) Want you in my rear window, baby, you're sick I want your love Love, love, love, I want your love (Love, love, love, I want your love) You know that I want you And you know that I need you ('cause I'm a free bitch, baby) I want it bad Your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh Caught in a bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, \"Oh la-la\" Want your bad romance Ra-ra-ah-ah-ah Roma-roma-ma Gaga, oh la-la Want your bad romance Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, fashion, baby Work it, move that bitch crazy Walk, walk, passion, baby Work it, I'm a free bitch, baby I want your love, and I want your revenge I want your love, I don't wanna be friends Je veux ton amour et je veux ta revanche Je veux ton amour, I don't wanna be friends (Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh) No, I don't wanna be friends (Want your bad romance) I don't wanna be friends Want your bad romance Want your bad romance I want your love, and I want your revenge You and me could write a bad romance Oh-oh-oh-oh-oh I want your love, and all your lover's revenge You and me could write a bad romance Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh (Want your bad romance) Caught in a bad romance (Want your bad romance) Oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh-oh (Want your bad romance) Caught in a bad romance Ra-ra-ah-ah-ah Roma, Roma-ma Gaga, Oh la-la Want your bad romance","bad romance"},
            {"Ayy, ayy, ayy, ayy (Ooh) Ooh, ooh, ooh, ooh (Ooh) Ayy, ayy Ooh, ooh, ooh, ooh Needless","Sunflower - Spider-Man: Into the Spider-Verse"},
            {"you're the sunflower","Sunflower - Spider-Man: Into the Spider-Verse"},
            {"Sunflower","Sunflower - Spider-Man: Into the Spider-Verse"},
            {"Sippin' on straight chlorine","Chlorine"},
            {"aight chline Let the vibe slide over me This beat is a chemical","Chlorine"},
            {"Today Today Today Twenty One Twenty One Twenty One","Twenty One"},
            {"Today Today Today Twe One Twe One Twenty One","Twenty One"},
            {"Twenty One","Twenty One"},
            {"You say you love me, I say you crazy We're nothing more than friends You're","FRIENDS"},
            {"I say you crazy We're nothing more than friends You're not my lover, more like a brother I known you since we were like ten,","FRIENDS"},
            {"I say you crazy We're not my lover, other I known you since we were like ten,","FRIENDS"},
            {"we don't talk anymore", "We Don’t Talk Anymore"},
            {"why you gotta be so rude", "Rude"},
            {"tonight we are young", "We Are Young (feat. Janelle Monáe) - Acoustic [Acoustic]"},
            {"Give me a second I, I need to get my story straight My friends are in the bathroom Getting higher than the", "We Are Young (feat. Janelle Monáe) - Acoustic [Acoustic]"},

            {"i'm in love with the shape of you", "Shape of You"},
            {"baby baby baby oh", "Baby"},
            {"i knew you were trouble when you walked in", "I Knew You Were Trouble"},
            {"don't believe me just watch", "Uptown Funk"},
            {"we're gonna let it burn burn burn", "Burn"},
            {"call me maybe", "Call Me Maybe"},
            {"but i set fire to the rain", "Set Fire to the Rain"},
            {"just gonna stand there and watch me burn", "Love the Way You Lie"},
            {"oh i think that i found myself a cheerleader", "Cheerleader - Felix Jaehn Remix Radio Edit"},
            {"I used to bite my tongue and hold my breath Scared to rock the boat and make a mess So ", "Roar"},
            {"when i met you in the summer", "Summer"},
            {"oh oh oh i'm falling so i'm taking my time on my ride", "Ride"},
            {"i just wanna feel this moment", "Feel This Moment"},
            {"you make me feel like i'm living a teenage dream", "Teenage Dream"},
            {"say something i'm giving up on you", "Say Something"},
            {"There was a time I used to look into my father's eyes In a happy home, I was a king, I had a golden throne ", "Don't You Worry Child - Radio Edit"},
            {"but i'm only human and i bleed when i fall down", "Human"},
            {"take me to church", "Take Me to Church"},
            {"i wanna swing from the chandelier", "Chandelier"},
            {"this is my fight song", "Fight Song"},
            {"and we danced all night to the best song ever", "Best Song Ever"},
            {"so wake me up when it's all over", "Wake Me Up"},
            {"lately i've been i've been losing sleep", "Counting Stars"},
            {"hey soul sister ain't that mister mister on the radio", "Hey, Soul Sister"},
            {"do you ever feel like a plastic bag", "Firework"},
            {"clap along if you feel like a room without a roof", "Happy - From \"Despicable Me 2"},
            {"i can't feel my face when i'm with you", "Can't Feel My Face"},
            {"baby pull me closer in the backseat of your rover", "Closer"},
            {"if you gave me a chance i would take it", "Rather Be"},
            {"shot through the heart and you're to blame", "You Give Love a Bad Name"},
            {"my mama don't like you and she likes everyone", "Love Yourself"},
            {"i'm off the deep end watch as i dive in", "Shallow"},
            {"she said where'd you wanna go how much you wanna risk are you looking for somebody with some superhuman", "Something Just Like This"},
            {"i see a little silhouetto of a man", "Bohemian Rhapsody"},
            {"you'll never see me again", "You'll Never See Me Again"},
            {"doh (Aww, ow) This hit, that ice cold Michelle Pfeiffer, that white gold This one, for them hood girls Them good girls, s", "Uptown Funk"},  // Chorus
            {"we're gonna let it burn burn burn burn", "Burn"},  // Chorus
            {"hey i just met you and this is crazy but here's my number", "Call Me Maybe"},  // Starting lines
            {"but i set fire to the rain watched it pour as i touched your face", "Set Fire to the Rain"},  // Chorus
            {"just gonna stand there and watch me burn but that's all right", "Love the Way You Lie"},  // Chorus
            {"oh i think that i found myself a cheerleader", "Cheerleader"},  // Chorus
            {"i got the eye of the tiger a fighter", "Roar"},  // Chorus
            {"when i met you in the summer to my heartbeat sound", "Summer"},  // Starting lines
            {"oh oh oh i'm falling so i'm taking my time on my ride", "Ride"},  // Chorus
            {"i just wanna feel this moment", "Feel This Moment"},  // Chorus
            {"you make me feel like i'm living a teenage dream", "Teenage Dream"},  // Chorus
            {"say something i'm giving up on you", "Say Something"},  // Chorus
            {"don't you worry child see heaven's got a plan for you", "Don't You Worry Child"},  // Chorus
            {"but i'm only human and i bleed when i fall down", "Human"},  // Chorus
            {"take me to church i'll worship like a dog at the shrine of your lies", "Take Me to Church"},  // Chorus
            {"i wanna swing from the chandelier from the chandelier", "Chandelier"},  // Chorus
            {"this is my fight song take back my life song", "Fight Song"},  // Chorus
            {"if you love me let me go", "This Is Gospel"},  // Starting line
            {"and we danced all night to the best song ever", "Best Song Ever"},  // Chorus
            {"so wake me up when it's all over", "Wake Me Up"},  // Chorus
            {"lately i've been i've been losing sleep dreaming about the things that we could be", "Counting Stars"},  // Starting lines
            {"hey soul sister ain't that mister mister on the radio", "Hey, Soul Sister"},  // Chorus
            {"do you ever feel like a plastic bag drifting through the wind", "Firework"},  // Starting lines
            {"just give me a reason just a little bit's enough", "Just Give Me a Reason"},  // Chorus
            {"clap along if you feel like a room without a roof", "Happy"},  // Chorus
            {"i can't feel my face when i'm with you but i love it", "Can't Feel My Face"},  // Chorus
            {"baby pull me closer in the backseat of your rover", "Closer"},  // Chorus
            {"if you gave me a chance i would take it", "Rather Be"},  // Starting line
            {"i'm gonna pop some tags only got twenty dollars in my pocket", "Thrift Shop"},  // Chorus
            {"we're happy free confused and lonely at the same time", "22"},  // Starting line
            {"my mama don't like you and she likes everyone", "Love Yourself"},  // Starting line
            {"i'm off the deep end watch as i dive in i'll never meet the ground", "Shallow"},  // Chorus
            {"she said where'd you wanna go how much you wanna risk", "Something Just Like This"},  // Starting line
            {"in the night she’s dancing to relieve the pain", "In the Night"},  // Starting line
            {"When your legs don't work like they used to before And I can't sweep you off of your feet Will your mouth", "Thinking Out Loud"},  // Starting line
            {"i need a one dance got a hennessy in my hand", "One Dance"},  // Starting line
            {"so baby now take me into your loving arms", "Thinking Out Loud"},  // Chorus
            {"i'm just gonna shake shake shake shake shake", "Shake It Off"}  // Chorus
        };

        int passed = 0;

        // Run test cases
        for (String[] testCase : testCases) {
            String lyrics = testCase[0];
            String expectedSong = testCase[1];

            System.out.printf("Testing lyrics: \"%s\" | Expected song: \"%s\"%n", lyrics, expectedSong);
            int result = TestingMain.searchLyrics(esClient, lyrics, expectedSong);
            if (result == 1) {
                passed++;
                System.out.println("Test passed!");
            } else {
                System.out.println("Test failed!");
            }
            System.out.println("---------------------------------------------------------");
        }

        System.out.printf("Summary: %d/%d test cases passed.%n", passed, testCases.length);
    }
}
