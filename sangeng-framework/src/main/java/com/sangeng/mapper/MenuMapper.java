package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-22 23:27:29
 */
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT\n" +
            "            DISTINCT m.perms\n" +
            "        FROM\n" +
            "            `sys_user_role` ur\n" +
            "                LEFT JOIN `sys_role_menu` rm ON ur.`role_id` = rm.`role_id`\n" +
            "                LEFT JOIN `sys_menu` m ON m.`id` = rm.`menu_id`\n" +
            "        WHERE\n" +
            "            ur.`user_id` = #{id} AND\n" +
            "            m.`menu_type` IN ('C','F') AND\n" +
            "            m.`status` = 0 AND\n" +
            "            m.`del_flag` = 0")
    List<String> selectPermsByUserId(Long id);



    @Select("SELECT\n" +
            "          DISTINCT m.id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.status, IFNULL(m.perms,'') AS perms, m.is_frame,  m.menu_type, m.icon, m.order_num, m.create_time\n" +
            "        FROM\n" +
            "            `sys_menu` m\n" +
            "        WHERE\n" +
            "            m.`menu_type` IN ('C','M') AND\n" +
            "            m.`status` = 0 AND\n" +
            "            m.`del_flag` = 0\n" +
            "        ORDER BY\n" +
            "            m.parent_id,m.order_num")
    List<Menu> selectAllRouterMenu();



    @Select("SELECT\n" +
            "          DISTINCT m.id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.status, IFNULL(m.perms,'') AS perms, m.is_frame,  m.menu_type, m.icon, m.order_num, m.create_time\n" +
            "        FROM\n" +
            "            `sys_user_role` ur\n" +
            "            LEFT JOIN `sys_role_menu` rm ON ur.`role_id` = rm.`role_id`\n" +
            "            LEFT JOIN `sys_menu` m ON m.`id` = rm.`menu_id`\n" +
            "        WHERE\n" +
            "            ur.`user_id` = #{userId} AND\n" +
            "            m.`menu_type` IN ('C','M') AND\n" +
            "            m.`status` = 0 AND\n" +
            "            m.`del_flag` = 0\n" +
            "        ORDER BY\n" +
            "            m.parent_id,m.order_num")
    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    @Select(" select m.id\n" +
            "        from sys_menu m\n" +
            "        left join sys_role_menu rm on m.id = rm.menu_id\n" +
            "        where rm.role_id = #{roleId}\n" +
            "        order by m.parent_id, m.order_num")
    List<Long> selectMenuListByRoleId(Long roleId);
}
