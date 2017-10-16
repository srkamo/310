#Create Users 
INSERT INTO Users(fName, lName, email, password)
VALUES("Bob", "Muller", "bmuller@usc.edu", "password1");

INSERT INTO Users(fName, lName, email, password)
VALUES("Natalie", "Monger", "nmonger@usc.edu", "password2"); 

INSERT INTO Users(fName, lName, email, password)
VALUES("Morgan", "Bent", "mbent@usc.edu", "password3"); 

INSERT INTO Users(fName, lName, email, password)
VALUES("Peter" , "Tillman", "ptillman@usc.edu", "password4"); 

INSERT INTO Users(fName, lName, email, password)
VALUES("Sebastian" , "Rinkema", "srinkema@usc.edu", "password5"); 



#Create Entreprenurship Club Poll
INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd )
VALUES(1, "Best Entreprenurship Club", "http://smurfitschoolblog.com/wp-content/uploads/2017/04/entrepreneur.jpg", 0, 1, '2019-12-11 23:59:59');

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
INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(1, "Pizza Studio", "Pizza in a studio", 0, "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png", 0, 1, '2019-10-10 23:59:59');

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(2, "Blaze Pizza", "Pizza that is Blazin' ", 0, "https://s3-media4.fl.yelpcdn.com/bphoto/_xLCa8wyxWUygxHJvkodNA/ls.jpg", 0, 1, '2019-04-10 23:59:59');

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(3, "Domino's",  "Pizza that is good", 0, "https://cache.dominos.com/olo/5_14_2/assets/build/images/promo/dominos_social_logo.jpg", 0, 1, '2019-04-05 23:59:59');

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(4, "Pizza Hut", "Pizza with the Hut", 0, "https://upload.wikimedia.org/wikipedia/az/thumb/9/99/Pizza_Hut.svg/1200px-Pizza_Hut.svg.png", 0, 1, '2019-08-05 23:59:59');


#Create Pizza Entity Tags 
INSERT INTO EntityTags(tagID, entityID, title)
VALUES(1, 1, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(2 ,2, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(3, 3, "Pizza");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(4, 4, "Pizza");



#Create Computer Science Entities
INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(5, "CS 104",  "The worst class, hands down", 0, "http://www-bcf.usc.edu/~aaroncot/aaron.jpg", 0, 0, '2019-04-06 23:59:59');

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(6, "CS 170", "Not as bad as 104", 0, "http://www-bcf.usc.edu/~aaroncot/aaron.jpg", 0, 1, '2019-04-03 23:59:59');

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(7, "CS 201", "Not too bad",  0, "https://viterbi.usc.edu/directory/images/2dafc9bc59c736884ae57b7235d5bc67.jpg", 0, 1, '2019-04-06 23:59:59');

INSERT INTO Entities(entityID, title, description, rating, image, numViews, isInfinite, timeEnd)
VALUES(8, "CS 270", "Makes 170 look like a joke", 0, "http://www-bcf.usc.edu/~aaroncot/aaron.jpg", 0, 1, '2019-01-03 23:59:59');


#Create Computer Science Entity Tags 
INSERT INTO EntityTags(tagID, entityID, title)
VALUES(5, 5,  "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(6, 6,  "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(7, 7,  "Computer Science");

INSERT INTO EntityTags(tagID, entityID, title)
VALUES(8, 8, "Computer Science");

#Create Computer Science Poll 
INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd )
VALUES(2, "Best Computer Science Class", "http://www.austincc.edu/sites/default/files/Computer-Science_10282016.jpg ", 0, 1, '2019-02-03 23:59:59');

#Create Computer Science Poll Tags 
INSERT INTO PollTags(tagID, pollID, title)
VALUES(3, 2 , "Computer Science");

#Create Computer Science Poll Options 
INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(5, 2, "CS 104", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(6, 2, "CS 170", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(7, 2, "CS 201", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(8, 2, "CS 270", 0);


#Create Pizza Poll 
INSERT INTO Polls(pollID, title, image, numViews, isInfinite, timeEnd)
VALUES(3, "Best Pizza at USC", "https://www.cicis.com/media/1138/pizza_trad_pepperoni.png", 0, 0, '2019-04-05 23:59:59'); 

#Create Pizza Poll Tags
INSERT INTO PollTags(tagID, pollID, title) 
VALUES(4, 3, "Pizza");

INSERT INTO PollTags(tagID, pollID, title) 
VALUES(5, 3, "Food"); 

#Create Pizza Poll Options
INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(9, 3, "Pizza Studio", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(10, 3, "Blaze Pizza", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(11, 3, "Dominos", 0);

INSERT INTO Options(optionID, pollID, title, numVotes)
VALUES(12, 3, "Pizza Hut", 0);
