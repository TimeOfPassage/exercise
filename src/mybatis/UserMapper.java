package mybatis;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {

	User queryUser(String id);
	
	@Select("select * from t_system_user_0 where id = #{id}")
	User queryUser2(String id);

}
