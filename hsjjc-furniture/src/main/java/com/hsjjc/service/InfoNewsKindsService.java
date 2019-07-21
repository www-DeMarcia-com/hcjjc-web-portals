package com.hsjjc.service;



import java.util.List;

import org.springframework.data.domain.Page;


import com.hsjjc.model.InfoNews;
import com.hsjjc.model.InfoNewsKinds;

public interface InfoNewsKindsService {
	List<InfoNewsKinds> queryAllInfoNewsKinds();
}
