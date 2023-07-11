package com.example.driftschoolgradingsystem.utils;

public class ScoreCalculator {
    public static String calculateGrade(int marks) {
        if (marks >= 0 && marks <= 20) {
            return "E";
        } else if (marks >= 21 && marks <= 27) {
            return "D-";
        } else if (marks >= 28 && marks <= 35) {
            return "D";
        } else if (marks >= 36 && marks <= 43) {
            return "D+";
        } else if (marks >= 44 && marks <= 52) {
            return "C-";
        } else if (marks >= 53 && marks <= 60) {
            return "C";
        } else if (marks >= 61 && marks <= 68) {
            return "C+";
        } else if (marks >= 69 && marks <= 75) {
            return "B-";
        } else if (marks >= 76 && marks <= 82) {
            return "B";
        } else if (marks >= 83 && marks <= 89) {
            return "B+";
        } else if (marks >= 89 && marks <= 95) {
            return "A-";
        } else if (marks >= 95 && marks <= 100) {
            return "A";
        } else {
            return "Invalid";
        }
    }

    public static int calculatePoints(int marks) {
        switch (calculateGrade(marks)) {
            case "E":
                return 1;
            case "D-":
                return 2;
            case "D":
                return 3;
            case "D+":
                return 4;
            case "C-":
                return 5;
            case "C":
                return 6;
            case "C+":
                return 7;
            case "B-":
                return 8;
            case "B":
                return 9;
            case "B+":
                return 10;
            case "A-":
                return 11;
            case "A":
                return 12;
            default:
                return 0;
        }
    }
    public static String calculateFinalGrade(double meanPoints) {
        if (meanPoints < 1.5) {
            return "E";
        } else if (meanPoints >= 1.5 && meanPoints < 2.5) {
            return "D-";
        } else if (meanPoints >= 2.5 && meanPoints < 3.5) {
            return "D";
        } else if (meanPoints >= 3.5 && meanPoints < 4.5) {
            return "D+";
        } else if (meanPoints >= 4.5 && meanPoints < 5.5) {
            return "C-";
        } else if (meanPoints >= 5.5 && meanPoints < 6.5) {
            return "C";
        } else if (meanPoints >= 6.5 && meanPoints < 7.5) {
            return "C+";
        } else if (meanPoints >= 7.5 && meanPoints < 8.5) {
            return "B-";
        } else if (meanPoints >= 8.5 && meanPoints < 9.5) {
            return "B";
        } else if (meanPoints >= 9.5 && meanPoints < 10.5) {
            return "B+";
        } else if (meanPoints >= 10.5 && meanPoints < 11.5) {
            return "A-";
        } else {
            return "Not Graded";
        }
    }

}
