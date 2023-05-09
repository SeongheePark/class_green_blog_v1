package com.tenco.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 100)
	private String title;
	@Lob // 대용량 데이터
	private String content;
	@ColumnDefault("0") // 숫자형은 그냥 써주면 int가 된다~!
	private int count;
	// Board와 User 관계는 N : 1
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	// 리플에 대한 정보가 없다
	// Board 정보를 가지고 올 때 댓글 정보도 가져와야한다
	// Board와 Reply의 연관관계 1:N
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // LAZY : 기본값
	private List<Reply> reply;
	// 주의 : board 테이블에 reply_id 컬럼이 필요한가 (x) -> mappedBy 설정
	// mappedBy 설정 시 board 테이블에 컬럼이 생성되지 않음
	// object가 생성될 때 즉, 데이터를 가져올때 알아서 join 처리하여 데이터만 가져온다
	
	
	@CreationTimestamp // now()
	private Timestamp createdDate;
}
