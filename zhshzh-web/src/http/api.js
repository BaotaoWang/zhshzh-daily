import {GET, POST, PUT, DELETE} from '@/http/axios'

// 登录
export const login = params => POST('/login', params)
// 查询有权限查看的菜单
export const getMenuTree = params => GET('/menuInfos', params)
// 查询所有的菜单
export const listMenuInfos = params => GET('/menuInfos/all/admin', params)
// 查询所有的菜单(级联选择器数据模型)
export const listCascaderSysMenuInfos = params => GET('/menuInfos/cascader/admin', params)
// 新增菜单
export const addMenu = params => POST('/menuInfos/admin', params)
// 修改菜单状态
export const changeMenuState = (id, params) => PUT('/menuInfos/menuState/' + id + '/admin', params)
// 修改菜单
export const updateMenu = (id, params) => PUT('/menuInfos/' + id + '/admin', params)
// 删除菜单
export const deleteMenu = (id, params) => DELETE('/menuInfos/' + id + '/admin', params)
// 生成代码
export const generator = params => POST('/generator/admin', params)
// 查询数据库中所有的表名
export const listTableNames = params => GET('/generator/admin', params)
