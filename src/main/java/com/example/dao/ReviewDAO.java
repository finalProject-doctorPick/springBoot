package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewDAO {

	public void deleteReviewId(Integer reviewId);

}
