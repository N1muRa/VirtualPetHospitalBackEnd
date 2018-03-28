# VirtualPetHospitalBackEnd
VirtualPetHospital Project BackEnd

## 注意：

* Controller类需添加@Controller注解，否则不会扫描该Controller，导致无法使用
* ServiceImpl类添加@Service注解
* DaoImpl类添加@Reposity注解
* Entity类添加@Entity注解
* Entity类方法@Column注解下name需与数据库对应字段名相同
* @RequestMapping中设置produces="application/json"可将返回值定为json类型
* 根据数据库账户是否使用SSL连接设置config.properties中jdbc.url最后使用添加useSSL=true或false
