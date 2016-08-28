package hw31;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;

@Entity(value="students")
public class Student {
	@Id
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	
	@Embedded
	private List<Score> scores = new ArrayList<Score>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", scores=");
		builder.append(scores);
		builder.append("]");
		return builder.toString();
	}				
}

@Embedded
class Score{
	private String type;
	private double score;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Score [type=");
		builder.append(type);
		builder.append(", score=");
		builder.append(score);
		builder.append("]");
		return builder.toString();
	}
	
	
}