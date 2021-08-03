class Twitter {
    
    private static int timeStamp = 0;
    
    private Map<Integer, User> userMap;
    
    private class Tweet {
        public int id;
        public int postedTime;
        
        public Tweet(int id) {
            System.out.println("Creating tweet with id " +id);
            this.id = id;
            this.postedTime = timeStamp++;
        }
        
        public int getId() {
            return id;
        }
        
        public int getPostedTime() {
            return postedTime;
        }
        
        
    }
    
    private class User {
        public int userId;
        public List<Integer> following;
        public List<Tweet> tweets;
        
        
        public User(int id) {
            this.userId = id;
            this.following = new ArrayList<Integer>();
            this.following.add(this.userId);
            this.tweets = new ArrayList<Tweet>();
        }
        
        public List<Tweet> getTweets() {
            return tweets;
        }
        
        public List<Integer> getFollowing() {
            return following;
        }
        
        public void followUser(int followId) {
            following.add(followId);
        }
        
        public void unFollowUser(int followId) {
            System.out.println("Getting ehre " + followId);
            System.out.println("Followers " + following.toString());
            if(following.contains(followId)) {
                following.remove(followId);
            }
        }
        
        public void postTweet(int tweetId) {
            this.tweets.add(new Tweet(tweetId));
        }
    
        
        
        
    }

    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)) {
            User user = new User(userId);
            userMap.put(userId, user);
        }
        userMap.get(userId).postTweet(tweetId);
        
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User user = userMap.get(userId);
        List<Tweet> userTweets = user.getTweets();
        for(Integer followee: user.following) {
            User followedUser = userMap.get(followee);
            userTweets.addAll(followedUser.getTweets());    
        }
        List<Integer> res = new ArrayList<Integer>();
        for(Tweet results: userTweets) {
            res.add(results.id);
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)) {
            User user = new User(followerId);
            userMap.put(followerId, user);
        }
        if(!userMap.containsKey(followeeId)) {
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        userMap.get(followerId).followUser(followeeId);       
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId==followeeId) {
            return;
        }
        userMap.get(followerId).unFollowUser(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
