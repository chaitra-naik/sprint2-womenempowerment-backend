package com.example.womenempowerment.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Feedback {
	@Id
	@Column(name="Fedback_ID")
	@GeneratedValue
	int feedbackId;
	
	int rating;
	String comments;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="User_ID")
	User user;
	LocalDate feedbackdate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Training_Course_ID")
	TrainingCourse training;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Scheme_ID")
	Scheme scheme;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedback(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDate getFeedbackdate() {
		return feedbackdate;
	}
	public void setFeedbackdate(LocalDate feedbackdate) {
		this.feedbackdate = feedbackdate;
	}
	public TrainingCourse getTraining() {
		return training;
	}
	public void setTraining(TrainingCourse training) {
		this.training = training;
	}
	public Scheme getScheme() {
		return scheme;
	}
	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}
	public Feedback(int feedbackId, int rating, String comments,
			 User user,LocalDate feedbackdate, TrainingCourse training, Scheme scheme) {
		super();
		this.feedbackId = feedbackId;
		this.rating=rating;
		this.comments = comments;
		this.user = user;
		this.feedbackdate = feedbackdate;
		this.training = training;
		this.scheme = scheme;
	}
	public Feedback() {
		super();
	}
	
}
