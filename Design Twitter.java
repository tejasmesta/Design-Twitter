class Twitter {
    
    private class Tweet {
        private int userId;
        private int tweetId;
        private int time;
        
        public Tweet(int userId, int tweetId, int time){
            this.userId = userId;
            this.tweetId = tweetId;
            this.time = time;
        }  
    }
    
    HashMap<Integer,HashSet<Integer>> following;
    HashMap<Integer,Tweet> tweets;
    int globalTime;

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
        globalTime = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        
        Tweet post = new Tweet(userId,tweetId,++globalTime);
        tweets.put(globalTime,post);
        
        follow(userId,userId);
    }
    
    public List<Integer> getNewsFeed(int userId) {

        List<Integer> ret = new ArrayList<>();
        int currTime = globalTime;
        
        while(tweets.containsKey(currTime) && ret.size() < 10){
            Tweet t = tweets.get(currTime);
            if(following.get(userId).contains(t.userId)){
                ret.add(t.tweetId);
            }
            currTime--;
        }
        return ret;
    }
    
    public void follow(int followerId, int followeeId) {
        
        if(!following.containsKey(followerId)){
            following.put(followerId,new HashSet<>());
        }
        following.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        
        if(following.containsKey(followerId)){
            following.get(followerId).remove(followeeId);
        }
    }
}
