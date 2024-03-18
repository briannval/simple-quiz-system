# My Personal Project

# Brian Adhitya 27670264

## An online quiz system

## Introduction

Students at UBC are very passionate about practising the exam materials before /
prior to the exam. Usually, they would use past midterms or past finals from their
instructor to practise. However, not all instructors will release past exams, so students
don't know where to practise. They thought about redoing all their past assignments, worksheets,
and tutorials, but it doesn't give the same feeling as attempting an exam because they have
done those tasks beforehand and they are simply redoing it. So they try answer other people's
confusions on Piazza. Think about it, what if there was a way to make Piazza, but for **quizzes** instead of questions?

## Overview

**"There is no better way to learn than to teach"** -Robert Oppenheimer
... or than to *test*.
This online quiz system will allow students to make custom quizzes based on the material which will be for other students
to practise. Other students will be able to go through these quizzes under exam settings. By creating quizzes like this, it is
guaranteed to enforce student learning, because a higher level of understanding is required to create well-worded problems, and
be prepared to explain the solutions later on. This will create greater levels of insight for all other students as well, and hopefully,
just hopefully, ...increase the *average* for many classes.


## Goal

What this project will *do*:
- Students can **create** a quiz for other students to attempt
- Questions can be of a number answer format, a multiple choice format, or an essay format
- Setting the questions in *three different formats* will allow flexiblity from courses like CPSC, CHEM, MATH, to even 
ENGL
- There will be a great variety of quizzes since all students are given the option to create quizzes.
- Students will be able to star another student's quiz.
- (ADDITIONAL) Add an instructor panel, which may give feedback on student's self-made exams, and 

An example of text with **bold** and *italic* fonts.  

## User Story

X and Y requirement
X = question
Y = quiz
- As a user, I want to be able to create a quiz. (IMPLEMENTED)
  Example of ADD multiple X to Y, where X = questions, and Y = quiz. Users will be able to add multiple questions. 
- As a user, I want to be able to attempt another user's quiz. (IMPLEMENTED)
  Example of LIST all the X in Y, where X = questions, and Y = quiz. Users will be able to see all the questions.
- As a user, I want to be able to get feedback on my attempt. (IMPLEMENTED)
- As a user, I want to see all the quizzes that are available to try (IMPLEMENTED)
- As a user, I want to be able to star a quiz I have just attempted (IMPLEMENTED)
- As a user, I want to be able to create questions. (IMPLEMENTED)
- As a user, I want to be able to load quizzes data from a JSON file. (IMPLEMENTED)
- As a user, I want to be able to save quizzes data to a JSON file. (IMPLEMENTED)



# Stack
- Java JUnit testing
- Java Swing UI


# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking create
quiz, this will allow you to create a new quiz and add it to the list of quizzes (add X to Y). There will be prompts to
guide the user in the process of creating a quiz.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking sort
on the View All Quizzes panel. This will allow you to sort the quizzes either 1. alphabetically, or 2. by stars, both
ascending or descending
- You can locate my visual component by seeing the main panel, icons added to panes etc
- You can save the state of my application by clicking the Save Data button on the main panel
- You can reload the state of my application by clicking the Load Data button on the main panel

