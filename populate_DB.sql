#Create Users 
INSERT INTO Users(fName, lName, email, password)
VALUES("Bob", "Muller", "bmuller@usc.edu", "7c6a180b36896a0a8c02787eeafb0e4c");

INSERT INTO Users(fName, lName, email, password)
VALUES("Natalie", "Monger", "nmonger@usc.edu", "6cb75f652a9b52798eb6cf2201057c73"); 

INSERT INTO Users(fName, lName, email, password)
VALUES("Morgan", "Bent", "mbent@usc.edu", "819b0643d6b89dc9b579fdfc9094f28e"); 

INSERT INTO Users(fName, lName, email, password)
VALUES("Peter" , "Tillman", "ptillman@usc.edu", "34cc93ece0ba9e3f6f235d4af979b16c"); 

INSERT INTO Users(fName, lName, email, password)
VALUES("Sebastian" , "Rinkema", "srinkema@usc.edu", "db0edd04aaac4506f7edab03ac855d56"); 



#Create Entreprenurship Club Poll
INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(1, "Best Entreprenurship Club", "http://smurfitschoolblog.com/wp-content/uploads/2017/04/entrepreneur.jpg", 0, 1, '2019-12-11 23:59:59', 1, 0);

#Create Entreprenurship Club Poll Tags 
INSERT INTO PollTags(tagID, pollID, title)
VALUES(1, 1, "Entreprenurship");

INSERT INTO PollTags(tagID, pollID, title)
VALUES(2, 1, "Club");

#Create Entreprenurship Club Poll Options 
INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(1, 1, "SparckSC", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(2, 1, "Lava Lab", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(3, 1, "Sigma Eta Pi", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(4, 1, "Design for America USC", 0);





#Create Pizza Entities
INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(2, "Pizza Studio", "Pizza in a studio", 0, "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png", 0, 1, '2019-10-10 23:59:59', 1, 0);

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(3, "Blaze Pizza", "Pizza that is Blazin' ", 0, "https://s3-media4.fl.yelpcdn.com/bphoto/_xLCa8wyxWUygxHJvkodNA/ls.jpg", 0, 1, '2019-04-10 23:59:59', 1, 0);

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(4, "Domino's",  "Pizza that is good", 0, "https://cache.dominos.com/olo/5_14_2/assets/build/images/promo/dominos_social_logo.jpg", 0, 1, '2019-04-05 23:59:59', 1, 0);

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(5, "Pizza Hut", "Pizza with the Hut", 0, "https://upload.wikimedia.org/wikipedia/az/thumb/9/99/Pizza_Hut.svg/1200px-Pizza_Hut.svg.png", 0, 1, '2019-08-05 23:59:59', 1, 0);


#Create Pizza Entity Tags 
INSERT INTO EntityTags(tagID, entityID, title)
VALUES(3, 2, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(4, 2, "Food");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(5, 3, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(6, 3, "Food");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(7, 4, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(8, 4, "Food");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(9, 5, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(10, 5, "Food");



#Create Computer Science Entities
INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(6, "CS 104",  "The worst class, hands down", 0, "http://www-bcf.usc.edu/~aaroncot/aaron.jpg", 0, 0, '2019-04-06 23:59:59', 1, 0);

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(7, "CS 170", "Not as bad as 104", 0, "http://www-bcf.usc.edu/~aaroncot/aaron.jpg", 0, 1, '2019-04-03 23:59:59', 1, 0);

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(8, "CS 201", "Not too bad",  0, "https://viterbi.usc.edu/directory/images/2dafc9bc59c736884ae57b7235d5bc67.jpg", 0, 1, '2019-04-06 23:59:59', 1, 0);

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(9, "CS 270", "Makes 170 look like a joke", 0, "http://www-bcf.usc.edu/~aaroncot/aaron.jpg", 0, 1, '2019-01-03 23:59:59', 1, 0);

#Create Computer Science Entity Tags 
INSERT INTO EntityTags(tagID, entityID, title)
VALUES(11, 6,  "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(12, 6,  "Classes");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(13, 6,  "Professors");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(14, 7,  "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(15, 7,  "Classes");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(16, 6,  "Professors");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(17, 8,  "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(18, 8,  "Classes");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(19, 6,  "Professors");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(20, 9, "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(21, 9, "Classes");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(22, 6,  "Professors");

#Create Computer Science Poll 
INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd, creatorID, anonCreator )
VALUES(10, "Best Computer Science Class", "http://www.austincc.edu/sites/default/files/Computer-Science_10282016.jpg ", 0, 1, '2019-02-03 23:59:59', 1, 0);

#Create Computer Science Poll Tags 
INSERT INTO PollTags(tagID, pollID, title)
VALUES(3, 10 , "Computer Science");

INSERT INTO PollTags(tagID, pollID, title)
VALUES(4, 10 , "Classes");

#Create Computer Science Poll Options 
INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(5, 10, "CS 104", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(6, 10, "CS 170", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(7, 10, "CS 201", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(8, 10, "CS 270", 0);


#Create Pizza Poll 
INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd, creatorID, anonCreator)
VALUES(11, "Best Pizza at USC", "https://www.cicis.com/media/1138/pizza_trad_pepperoni.png", 0, 0, '2019-04-05 23:59:59', 1, 0); 

#Create Pizza Poll Tags
INSERT INTO PollTags(tagID, pollID, title) 
VALUES(5, 11, "Pizza");

INSERT INTO PollTags(tagID, pollID, title) 
VALUES(6, 11, "Food");

#Create Pizza Poll Options
INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(9, 11, "Pizza Studio", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(10, 11, "Blaze Pizza", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(11, 11, "Dominos", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(12, 11, "Pizza Hut", 0);


INSERT INTO Blogs(blogID, creatorID, title, description, image, content, dateCreated)
VALUES(1, 1, "How I Successfully Landed an Internship", "As a junior, one of my primary goals this semester was to secure an internship opportunity within the medical device industry.", "https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX3038239.jpg", "As a junior, one of my primary goals this semester was to secure an internship opportunity within the medical device industry so I could personally experience what the day to day life of a biomedical engineer entails. And I’m fortunate to say that I was able to achieve it- I’ll be an Operations Intern at Abbott Laboratories, a global healthcare company that conducts innovative research and manufactures products for human health. During my past summer, I was able to secure an opportunity to study abroad in Rome, Italy (I wrote about it! Check it out!). Therefore, I didn’t need to invest myself heavily in last year’s recruitment cycle. This year, I knew that landing an internship would require a lot of focus and determination. I’ve got to be honest. The opportunity was not handed to me. I put in hours of work – going to info sessions, corporate dinners, and career fairs, as well as researching my companies of interest and talking to former interns. The beauty of it all is that USC has this plethora of resources in place to connect its talented student body to the best companies across the world.", "0");

INSERT INTO Blogs(blogID, creatorID, title, description, image, content, dateCreated)
VALUES(2, 2, "FriendSHPE, LeaderSHPE… InternSHPE?", "Greetings friends!! How’s it going? I hope you’re doing well. Today I am here to tell you all about the SHPE National Conference that I attended last weekend.", "http://viterbiadmission.usc.edu/wp-content/uploads/2013/03/53751_10151360859556099_1260986421_o.jpg", "Greetings friends!! How’s it going? I hope you’re doing well. Today I am here to tell you all about the SHPE National Conference that I attended last weekend because it was a wild time.
First, you may be wondering, Vanessa, what is the SHPE National Conference? Let me tell you! SHPE is the Society of Hispanic Professional Engineers (read Camila’s blog about our USC chapter), and the National Conference is an annual giant conference where university and professional chapters from all over the country come together along with company representatives to attend workshops, networking events, and a two-day career fair to #get #those #jobs and develop your professional skills.
Some of the workshops I attended included a Lean Six Sigma workshop where I got white belt certified and a L’Oréal case study workshop.I also attended the Region 2 meeting (that’s the region USC is in) where SHPE-USC was recognized for our Outstanding Professional Development!
The career fair was similar to any other career fair experience except ginormous and most companies schedule interviews on the spot and many people get offered full time and internship positions before the weekend is even over! I was able to secure an interview with The Clorox Company!
So what did I learn? Networking is really tiring but it’s not nearly as hard as I made it out to seem. The first day of the career fair and during many of the workshops, I psyched myself out and got too nervous to talk to the company reps one on one but by the final day I had lost that fear and went for it. And that is when I got my interview! So my tip would be, remember the recruiters are human too, they aren’t as scary as they seem. Second, I would say prepare as much as you can beforehand. I had a midterm the Thursday the conference started and because I was so focused on that, I didn’t have as much time as I would have liked to research companies, plan which workshops to attend and make a game plan for the career fair. Overall, if it’s possible, try to do your research and plan ahead. I’ve been in SHPE since my freshman year, slowly getting more involved. I’ve made incredible FriendSHPEs and I’ve had the chance to develop my LeaderSHPE skills and now hopefully, this organization might just help me land that summer InternSHPE! If you want to learn more about SHPE, check out the SHPE-USC website and Instagram!
Have a great weekend!! byeeee", "0");

INSERT INTO Blogs(blogID, creatorID, title, description, image, content, dateCreated)
VALUES(3, 3, "Thrill Seekers Central", "I am originally from Vallejo, California. For those of you who may not know where this is, it is up in Northern California, in between the Napa Valley and Berkeley",
"https://timedotcom.files.wordpress.com/2016/05/six-flags-joker-roller-coaster.jpg?quality=85", "I am originally from Vallejo, California. For those of you who may not know where this is, it is up in Northern California, in between the Napa Valley and Berkeley. Back home, one of the main attractions is Six Flags Discovery Kingdom. It is your typical theme park with many adrenaline filled rides and tons of great junk food. Coming to LA, however, the usual attraction people think of is Disneyland. Now, don’t get me wrong, I love going to Disneyland for the experience, but I don’t go for those thrilling, adrenaline rushing rides. For that, I go to Six Flags Magic Mountain.

Magic Mountain is the same branch of theme parks like Discovery Kingdom, but it is a lot bigger and has “scarier” rides as compared to my hometown’s counterpart. My favorite ride, by far, is X2, which is also one of the most popular rides at the park. So, I suggest that if you visit, you ride this ride first so that the line isn’t so long. Check out the ride POV video here!

The key to riding as many rides as possible is to plan out your day before going. I have gone a few times this year already, and have noticed that when I plan out the day, I ride all the rides I want to, without being to rushed for time. Also, make sure you get there as soon as you can, and have your ticket beforehand. Pro-tip, if you are from the area, just buy a season pass. For literally $5 more, if not being the same price, you can get a season pass rather than a general admission. The season pass also has no blackout days, so you can go whenever you want!

So if you love thrills like I do, I would definitely suggest going to Magic Mountain. There are plenty of rides to go on, and you will not be disappointed.  And even as an extra plus, they have a full water park called Hurricane Harbor within the park for those hotter days!", "0");

INSERT INTO Blogs(blogID, creatorID, title, description, image, content, dateCreated)
VALUES(4, 3, "Career Fairs, Internships, and Jobs. Oh My!", "It’s October so what does that mean… interview season!!", "http://viterbivoices.usc.edu/wp-content/uploads/2014/10/Career.png",
"It’s October so what does that mean… interview season!! Yes it is also Halloween and fall and all of that fun stuff, but it’s also the time when Viterbi students start going through recruitment for jobs and internships. That may seem super early, because it is! Some companies interview in the fall and some interview in the spring, but in this blog I’ll tell you all a little bit about how fall job recruitment works at USC!

The first sign that it’s time to start thinking about jobs or internships is the career fair. There are actually two fairs, one that is USC wide and one for just Viterbi students. This year I went to just the Viterbi one, because I’m only looking for engineering internships for next summer! The Viterbi career fair has over 100 companies, so you can talk to basically any company you’re looking for.

To prepare for the career fair, I updated my resume, got my elevator pitch ready, and researched what companies I wanted to talk to. I got my best business casual outfit ready, and headed out to Trousdale (our main street on campus). I talked to 5 biomedical companies and gave them my resumes, and asked what the next steps were! Most say to apply online and wait for an invitation for an interview.

The step after the career fair is applications and interviews. Some companies, like one I just interviewed for, talk to students at the career fair, call them to set up an interview that night, and then do the interview and give out the positions within the next week. The process goes so fast!!

For the companies that hire in the fall, it’s great because if you get a job or internship you’re set for the summer and don’t have to look any more! However, if you don’t get it you can also apply to the hundreds of companies that recruit in the spring. Either way, you will be fine with getting a job as long as you use USC’s resources and put yourself out there!

I am still in the process on getting an internship, so updates to come on that! For all of you still in high school, know that it is so easy to get an internship at USC because there are sooo many connections, career fairs, and the #TrojanFamily of course.", "0");

INSERT INTO Blogs(blogID, creatorID, title, description, image, content, dateCreated)
VALUES(5, 2, "How to Cook Like an Adult: A Few Tips", "Cooking well in college doesn’t need to be hard, or expensive! ",
"https://static.pexels.com/photos/70497/pexels-photo-70497.jpeg", "Cooking well in college doesn’t need to be hard, or expensive! There are a few tips to keep in mind that can make your dinners better, your shopping runs cheaper, and increase your overall level of foody awesomeness. Let’s get started!

First, before you cook anything – figure out your grocery store run. Google a few recipe ideas if you’re struggling to figure out what to cook. Always make a list, especially if you head to the store when you’re hungry – that way you won’t overbuy.

Next, choose your grocery. If you’re into buying organic or normally like to cook a certain type of food, consider specialty stores. In general, I like to try and plan out the week if I’m going to be fairly busy, and stick to simpler meals for lunch during the middle of the day. Building up a stock of general spices, as well as staples like sugar, eggs, milk, bread, and butter is also important, as these ingredients will be much more versatile than you think.

Now onto the fun part – cooking your amazing meal. Keep in mind that becoming a decent cook is only as hard as following directions in a recipe. There are some techniques that take time to learn, but for the most part, following directions step by step and making sure that the proportions of ingredients are right will result in some pretty great results!

Finally, enjoy yourself. The process of cooking should be just as enjoyable as actually eating. If you’re not enjoying the cooking, other parts of working in the kitchen (like doing the dishes) might be a little much.

If you’re trying to take it to the next level, consider buying a few herbs and starting an herb garden in your apartment or house – herbs require fairly little upkeep, its a conversation starter, and they add a great deal of flavor to all of your recipes!

That’s all for now – Fight on!

-Naish", "0")