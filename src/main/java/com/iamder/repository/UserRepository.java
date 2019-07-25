package com.iamder.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.iamder.model.User;

@Repository
public class UserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private DynamoDBMapper mapper;

	public void insertIntoDynamoDB(User user) {

		mapper.save(user);
	}

	public User getOneUserDetails(String uuId, String lastName) {
		return mapper.load(User.class, uuId, lastName);
	}

	public void deleteStudentDetails(User user) {
		mapper.delete(user);
	}

	public DynamoDBSaveExpression buildDynamoDBSaveExpression(User user) {
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("uuId", new ExpectedAttributeValue(new AttributeValue(user.getUuId()))
				.withComparisonOperator(ComparisonOperator.EQ));
		saveExpression.setExpected(expected);
		return saveExpression;
	}
}