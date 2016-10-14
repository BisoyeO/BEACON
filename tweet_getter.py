#!/usr/bin/python
import tweepy
import csv #Import csv
auth = tweepy.auth.OAuthHandler('EEKGvrZfOiXN0xFfJSrwzLpg8', 'M11LDCJE8yPWg25ZJykMnp0FgAx12g9Cq93YOvmKLysnykJUsu')
auth.set_access_token('56809662-rMZA4TyAfmKsBEfr2jwwgbOO0EFIwVi2KzPMZUV3u', '0vDKFIyt83iW9Mvp53w7XC8O671LIn8S7ic4osv5fdTDN')

api = tweepy.API(auth)
# Open/Create a file to append data
csvFile = open('tweets_result.csv', 'a')
#Use csv Writer
csvWriter = csv.writer(csvFile)


search_text = "#evolution OR #evolve OR #speciation OR #darwin OR #evolutionofman OR #creation OR #adapt OR #naturalselection OR #Evo OR #macroevolution"
"""
search_result = api.search(search_text, rpp= 100, since="2016-07-01")
for tweet in search_result:
	csvWriter.writerow([tweet.created_at, tweet.text.encode('utf-8')])
	print tweet.created_at, tweet.text
	
"""
for tweet in tweepy.Cursor(api.search, q=search_text, language="en", since='2016-08-12').items(10):
	csvWriter.writerow([tweet.created_at, tweet.text.encode('utf-8')])
	print tweet.created_at