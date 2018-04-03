package services.twitter;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class PrintSampleStream {
   
	private static TwitterStream twitterStream;
    public static void read(ConfigurationBuilder cb) throws TwitterException {
    	
        UserStreamListener listener = new UserStreamListener() {
			
			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStatus(Status arg0) {
				// TODO Auto-generated method stub
				System.out.println("Status ! :" + arg0.getText());
			}
			
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserSuspension(long arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserProfileUpdate(User arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListUpdate(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListDeletion(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserListCreation(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUserDeletion(long arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUnfollow(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUnfavorite(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUnblock(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onRetweetedRetweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onQuotedTweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFriendList(long[] arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFollow(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFavoritedRetweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFavorite(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDirectMessage(DirectMessage arg0) {
				System.out.println("message ! : \n" + arg0.getText());
			}
			
			@Override
			public void onDeletionNotice(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onBlock(User arg0, User arg1) {
				// TODO Auto-generated method stub
				
			}
		}; {};
		TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(cb.build());
        twitterStream = twitterStreamFactory.getInstance(new AccessToken("980545698520354816-y3kvuX6kaCU3Y28N3upry3dItGIx8Rj", "RveOsdDIdSA5IeP64GGzUwMSOcX19YWkhSQw4j01RbsXe"));
        twitterStream.addListener(listener);
        twitterStream.user();
    }
}