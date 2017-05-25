package service.basicFunctions;

import java.util.List;

import database.models.GiftUser;

public interface GiftUserService {

	public List<GiftUser> findByHql(String hql);
	
	public GiftUser insert(GiftUser giftUser);
	
	public void update(GiftUser giftUser);
	
	public GiftUser find(Integer id);
	
}
