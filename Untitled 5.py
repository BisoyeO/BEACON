#!/usr/bin/python

import sys
import jsonpickle
import os
import tweepy
import csv #Import csv
auth = tweepy.auth.OAuthHandler('EEKGvrZfOiXN0xFfJSrwzLpg8', 'M11LDCJE8yPWg25ZJykMnp0FgAx12g9Cq93YOvmKLysnykJUsu')
auth.set_access_token('56809662-rMZA4TyAfmKsBEfr2jwwgbOO0EFIwVi2KzPMZUV3u', '0vDKFIyt83iW9Mvp53w7XC8O671LIn8S7ic4osv5fdTDN')

api = tweepy.API(auth)

searchQuery = '#evolution'  # this is what we're searching for
maxTweets = 10000000 # Some arbitrary large number
tweetsPerQry = 100  # this is the max the API permits
fName = 'tweets.txt' # We'll store the tweets in a text file.


# If results from a specific ID onwards are reqd, set since_id to that ID.
# else default to no lower limit, go as far back as API allows
sinceId = "2016-09-15"

# If results only below a specific ID are, set max_id to that ID.
# else default to no upper limit, start from the most recent tweet matching the search query.
max_id = -1L

tweetCount = 0
print("Downloading max {0} tweets".format(maxTweets))
with open(fName, 'w') as f:
	while tweetCount < maxTweets:
		try:
			if (max_id <= 0):
				if (not sinceId):
					new_tweets = api.search(q=searchQuery, count=tweetsPerQry)
				else:
					new_tweets = api.search(q=searchQuery, count=tweetsPerQry,
											since_id=sinceId)
			else:
				if (not sinceId):
					new_tweets = api.search(q=searchQuery, count=tweetsPerQry,
											max_id=str(max_id - 1))
				else:
					new_tweets = api.search(q=searchQuery, count=tweetsPerQry,
											max_id=str(max_id - 1),
											since_id=sinceId)
			if not new_tweets:
				print("No more tweets found")
				break
			for tweet in new_tweets:
				print(tweet.created_at, tweet.text)
			tweetCount += len(new_tweets)
			print("Downloaded {0} tweets".format(tweetCount))
			max_id = new_tweets[-1].id
		except tweepy.TweepError as e:
			# Just exit if any error
			print("some error : " + str(e))
			break

print ("Downloaded {0} tweets, Saved to {1}".format(tweetCount, fName))
