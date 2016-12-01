package com.itany.p2p.service;

import java.util.List;

import com.itany.p2p.entity.Apply;
import com.itany.p2p.entity.Test;

public interface TestService {
	public	List<Test>	getAllTest();
	public	int	getApply(Apply	apply);
	public	void	apply(Apply	apply);
	public	List<Apply>	getHaveApply(Apply	apply);
	public	void	deleteTest(Test	test);
	public	int	testCheck(Test	test);
	public	void	testAdd(Test	test);
}
