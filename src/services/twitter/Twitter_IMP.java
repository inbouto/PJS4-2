package services.twitter;

import java.util.List;
import java.util.stream.Collectors;

import core.ICore;
import core.Service;
import coreComponents.Core;
import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter_IMP implements Service {

	private ICore core;
	private Twitter twitter;
	
	public Twitter getTwitter() {
		return twitter;
	}

	public static void main(String[] args) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("DKto1g64rOeaY2mtBJSQid3Ga")
		  .setOAuthConsumerSecret("1ztzOqkbOoMFn8hFHv0jYKuI75lCRv3YZLOJqA7rrnNoTXgbK0")
		  .setOAuthAccessToken("980545698520354816-y3kvuX6kaCU3Y28N3upry3dItGIx8Rj")
		  .setOAuthAccessTokenSecret("RveOsdDIdSA5IeP64GGzUwMSOcX19YWkhSQw4j01RbsXe");
		
		try {
			PrintSampleStream.read(cb);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public Twitter_IMP(ICore core, int service_id){
		this.core = core;
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("DKto1g64rOeaY2mtBJSQid3Ga")
		  .setOAuthConsumerSecret("1ztzOqkbOoMFn8hFHv0jYKuI75lCRv3YZLOJqA7rrnNoTXgbK0")
		  .setOAuthAccessToken("980545698520354816-y3kvuX6kaCU3Y28N3upry3dItGIx8Rj")
		  .setOAuthAccessTokenSecret("RveOsdDIdSA5IeP64GGzUwMSOcX19YWkhSQw4j01RbsXe");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "twitter";
	}
	
	public void createTweet(String tweet) throws TwitterException {
	    twitter.updateStatus(tweet);
	}
	
	public List<String> searchtweets(String content) throws TwitterException {
		  
	    Query query = new Query(content);
	    QueryResult result = twitter.search(query);
	     
	    return result.getTweets().stream()
	      .map(item -> item.getText())
	      .collect(Collectors.toList());
	}
	
	
	

}
