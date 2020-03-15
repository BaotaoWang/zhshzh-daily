<template>
  <div>
    <el-button
      type="primary"
      @click="addMenu">添加</el-button>
    <el-button
      :loading=refreshMenuButton
      @click="refreshMenu">刷新</el-button>
    <el-table
      :data="menus"
      style="width: 100%; margin-bottom: 20px;"
      row-key="menuInfoId"
      default-expand-all
      v-loading="loading"
      element-loading-text="加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.2)"
      :tree-props="{children: 'children'}">
      <el-table-column
        prop="menuName"
        label="菜单名称">
      </el-table-column>
      <el-table-column
        prop="menuRoute"
        label="菜单路由">
      </el-table-column>
      <el-table-column
        prop="menuIcon"
        label="菜单图标">
        <template slot-scope="icon">
          <i :class="icon.row.menuIcon"></i>
        </template>
      </el-table-column>
      <el-table-column
        prop="menuOrder"
        label="菜单序号">
      </el-table-column>
      <el-table-column
        prop="menuDescription"
        label="菜单描述">
      </el-table-column>
      <el-table-column
        prop="disabled"
        label="菜单状态">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.disabled ? '禁用' : '启用'" placement="top">
            <el-switch
              v-model="scope.row.disabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value=false
              :inactive-value=true
              @change=changeState(scope.$index,scope.row)>
            </el-switch>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="editMenu(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteMenu(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      :lock-scroll="false"
      width="490px" >
      <edit-menu v-if="dialogVisible" :menuInfo="menuInfo" @closeMenuDialog="closeMenuDialog" />
    </el-dialog>
  </div>
</template>

<script>
import EditMenu from './editMenu'
import {listMenuInfos, changeMenuState, deleteMenu} from '@/http/api'

export default {
  name: 'menus',
  components: {EditMenu},
  data () {
    return {
      loading: false,
      dialogVisible: false,
      dialogTitle: '',
      refreshMenuButton: false,
      menus: [],
      menuInfo: {}
    }
  },
  comments: {
    'edit-menu': EditMenu
  },
  mounted () {
    this.listMenuInfos()
  },
  methods: {
    /**
     * 查询所有的菜单
     */
    listMenuInfos: function () {
      this.loading = true
      listMenuInfos().then(response => {
        if (response.code === 10000) {
          this.menus = response.data
          this.refreshMenuButton = false
          this.loading = false
        }
      })
    },
    /**
     * 刷新菜单树
     */
    refreshMenu () {
      this.refreshMenuButton = true
      this.listMenuInfos()
    },
    /**
     * 修改菜单状态（false:启用；true:禁用）
     */
    changeState (index, row) {
      let menuInfoId = row.menuInfoId
      let hasParent = row.parentId !== 0
      let hasChildren = row.children.length > 0
      let disabled = row.disabled
      let message = ''
      if (disabled) {
        // 如果禁用
        if (hasChildren) {
          // 如果有子菜单
          message = '子级菜单同时禁用，是否禁用？'
        } else {
          // 如果没有子菜单
          message = '是否禁用？'
        }
      } else {
        // 如果启用
        if (hasParent) {
          // 如果有父菜单
          message = '父级菜单同时启用，是否启用？'
        } else {
          // 如果没有父菜单
          message = '是否启用？'
        }
      }
      this.$confirm(message, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        lockScroll: false
      }).then(() => {
        let state = {disabled: disabled}
        // 修改菜单状态
        changeMenuState(menuInfoId, state).then(response => {
          if (response.code === 10000) {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            // 重新加载列表
            this.listMenuInfos()
          }
        })
      }).catch(() => {
        row.disabled = !disabled
      })
    },
    /**
     * 添加菜单
     */
    addMenu () {
      this.dialogVisible = true
      this.dialogTitle = '添加菜单'
      this.menuInfo = {}
    },
    /**
     * 编辑菜单
     * @param index
     * @param row
     */
    editMenu (index, row) {
      this.dialogVisible = true
      this.dialogTitle = '修改菜单'
      // 将选中行的数据赋值给menuInfo并传向子页面
      this.menuInfo = row
    },
    /**
     * 删除菜单
     * @param index
     * @param row
     */
    deleteMenu (index, row) {
      let hasChildren = row.children.length > 0
      let message = ''
      if (hasChildren) {
        message = '子级菜单同时删除，是否删除？'
      } else {
        message = '是否删除？'
      }
      this.$confirm(message, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        lockScroll: false
      }).then(() => {
        deleteMenu(row.menuInfoId).then(response => {
          if (response.code === 10000) {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            // 重新加载列表
            this.listMenuInfos()
          }
        })
      }).catch(() => {})
    },
    /**
     * 关闭dialog弹窗时的绑定事件
     */
    closeMenuDialog () {
      this.dialogVisible = false
      // 关闭dialog弹窗时重新加载菜单树
      this.listMenuInfos()
    }
  }
}
</script>

<style scoped>
</style>
