DROP DATABASE IF EXISTS knowItAll;
CREATE DATABASE knowItAll;
#like namespace, everything under USE command uses the StudentGrades DB
USE knowItAll; 

CREATE TABLE Users(
	#columns
	userID INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	fName VARCHAR(50) NOT NULL,
	lName VARCHAR(50) NOT NULL
);

CREATE TABLE Activities(
	activityID int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    userID int(11) NOT NULL,
    isAnon bool NOT NULL, 
    type VARCHAR(50) NOT NULL,
    subjectID int(11) NOT NULL,
    actionValue VARCHAR(50) NOT NULL,
    #cannot add value for a follower that does not exist in the Users table
    FOREIGN KEY fk1(userID) REFERENCES Users(userID)
);

CREATE TABLE Polls(
	pollID INT(11) PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    image VARCHAR(200) NOT NULL,
    numViews INT(11) NOT NULL,
    isInfinite bool NOT NULL,
    timeEnd timestamp NOT NULL
);

CREATE TABLE Options(
	optionID int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    pollID int(11) NOT NULL,
    title VARCHAR(50) NOT NULL,
    numVotes INT(11) NOT NULL,
    FOREIGN KEY fk1(pollID) REFERENCES Polls(pollID)
);

CREATE TABLE Entities(
	entityID INT(11) PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    rating INT(11) NOT NULL,
    image VARCHAR(200) NOT NULL,
    numViews INT(11) NOT NULL,
    isInfinite bool NOT NULL,
    timeEnd timestamp NOT NULL
);
    
CREATE TABLE PollTags(
	tagID INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    pollID INT(11) NOT NULL,
    title VARCHAR(50) NOT NULL,
    FOREIGN KEY fk1(pollID) REFERENCES Polls(pollID)
);

CREATE TABLE EntityTags(
	tagID INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    entityID INT(11) NOT NULL,
    title VARCHAR(50) NOT NULL,
    FOREIGN KEY fk1(entityID) REFERENCES Entities(entityID)
);


