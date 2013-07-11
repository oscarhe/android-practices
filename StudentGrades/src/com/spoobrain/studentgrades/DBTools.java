package com.spoobrain.studentgrades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper {

	public DBTools(Context applicationContext) {
		
		super(applicationContext, "studentGrades.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String createClassTable = "CREATE TABLE classes (" +
								  "classId INTEGER PRIMARY KEY, " +
								  "className TEXT, " +
								  "classType TEXT, " +
								  "classNumber INTEGER, " +
								  "classSection TEXT, " +
								  "classYear INTEGER)";
		
		db.execSQL(createClassTable);
		
		String createStudentTable = "CREATE TABLE students (" +
								    "studentId INTEGER PRIMARY KEY, " +
								    "studentName TEXT, " +
								    "classId INTEGER, " +
								    "FOREIGN KEY (classId) " +
								    "REFERENCES classes(classId) " +
								    "ON DELETE CASCADE)";
		
		db.execSQL(createStudentTable);
		
		String createStudentInfoTable = "CREATE TABLE student_info (" +
										"studentId INTEGER PRIMARY KEY, " +
										"studentDOB DATE, " +
										"studentGPA REAL, " +
										"FOREIGN KEY (studentId) " +
										"REFERENCES students(studentId) " +
										"ON DELETE CASCADE)";
		
		db.execSQL(createStudentInfoTable);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String query = "DROP TABLE IF EXISTS classes";
		
		db.execSQL(query);
		
		query = "DROP TABLE IF EXISTS students";
		
		db.execSQL(query);
		
		query = "DROP TABLE IF EXISTS student_info";
		
		db.execSQL(query);
		
		onCreate(db);
		
	}

}
