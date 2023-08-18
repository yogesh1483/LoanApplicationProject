package com.loanapp.main.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
	private int responseStatus;
	private String responseMsg;
	private Date responseDate;
	private T responseData;
}
