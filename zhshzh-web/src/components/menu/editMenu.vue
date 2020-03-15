<template>
  <div>
    <el-form :model="form" :rules="rules" ref="form" label-width="80px">
      <el-form-item label="父级菜单" prop="parentId" >
        <el-cascader
          v-model="form.parentId"
          :options="parentOptions"
          :props="{ expandTrigger: 'hover',checkStrictly: true }"
          :show-all-levels="false"
          @change="parentChange"
          class="input-width" />
      </el-form-item>
      <el-form-item label="菜单名称" prop="menuName" >
        <el-input v-model="form.menuName" clearable class="input-width" />
      </el-form-item>
      <el-form-item label="菜单路由" prop="menuRoute" >
        <el-input v-model="form.menuRoute" clearable class="input-width" />
      </el-form-item>
      <el-form-item label="菜单序号" prop="menuOrder" >
        <el-input-number
          :min="1"
          :max="10000"
          v-model.number="form.menuOrder"
          class="input-width" />
      </el-form-item>
      <el-form-item label="菜单图标" prop="menuIcon" >
        <el-input v-model="form.menuIcon" class="input-width" />
      </el-form-item>
      <el-form-item label="菜单描述">
        <el-input
          type="textarea"
          autosize
          v-model="form.menuDescription"
          clearable
          class="input-width" />
      </el-form-item>
      <el-form-item style="text-align: right;">
        <el-button @click="closeMenuDialog(false)" size="small">取 消</el-button>
        <el-button @click="closeMenuDialog(true)" size="small" type="primary">确 定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {listCascaderSysMenuInfos, addMenu, updateMenu} from '@/http/api'

export default {
  name: 'editMenu',
  data () {
    return {
      parentOptions: [],
      form: {
        menuInfoId: '',
        parentId: '',
        menuName: '',
        menuRoute: '',
        menuOrder: '',
        menuIcon: '',
        menuDescription: ''
      },
      rules: {
        parentId: [
          { required: true, message: '必选项', trigger: 'blur' }
        ],
        menuName: [
          { required: true, message: '必填项', trigger: 'blur' },
          { max: 32, message: '长度不得大于32个字符', trigger: 'blur' }
        ],
        menuRoute: [
          { required: true, message: '必填项', trigger: 'blur' },
          { max: 32, message: '长度不得大于32个字符', trigger: 'blur' }
        ],
        menuOrder: [
          { required: true, message: '必填项', trigger: 'blur' }
        ],
        menuIcon: [
          { max: 64, message: '长度不得大于32个字符', trigger: 'blur' }
        ],
        menuDescription: [
          { max: 200, message: '长度不得大于200个字符', trigger: 'blur' }
        ]
      }
    }
  },
  props: ['menuInfo'],
  mounted () {
    this.form.menuInfoId = this.menuInfo.menuInfoId
    // parentOptions中value是string类型，而parentId是int类型，不转字符串的话，页面初始化的时候不会回显。
    this.form.parentId = this.menuInfo.parentId ? this.menuInfo.parentId.toString() : '0'
    this.form.menuName = this.menuInfo.menuName
    this.form.menuRoute = this.menuInfo.menuRoute
    this.form.menuOrder = this.menuInfo.menuOrder
    this.form.menuIcon = this.menuInfo.menuIcon
    this.form.menuDescription = this.menuInfo.menuDescription
    // 查询菜单树
    this.listCascaderSysMenuInfos()
  },
  methods: {
    /**
     * 查询菜单树
     */
    listCascaderSysMenuInfos () {
      listCascaderSysMenuInfos().then(response => {
        if (response.code === 10000) {
          this.parentOptions = response.data
        }
      })
    },
    /**
     * 父菜单级联选择器的change事件
     * @param value
     */
    parentChange (value) {
      // 由于级联选择器返回值是数组，当选定值时，将数组中的最后一个值赋值给parentId
      this.form.parentId = value[value.length - 1]
    },
    /**
     * 点击取消或者确认时的事件，当点击确认时进行保存操作
     */
    closeMenuDialog (isSaveMenu) {
      if (!isSaveMenu) {
        // 点击取消时，直接关闭dialog弹窗
        this.$emit('closeMenuDialog')
      } else {
        // 点击确认时，进行保存
        this.saveMenu()
      }
    },
    /**
     * 保存菜单信息
     * 只要关闭dialog弹窗就触发这个方法，但是只有点确定的时候才向后台保存数据
     */
    saveMenu () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let menuInfoId = this.form.menuInfoId
          if (menuInfoId) {
            // 如果有menuInfoId，更新菜单信息
            updateMenu(menuInfoId, this.form).then(response => {
              if (response.code === 10000) {
                // 保存成功后关闭dialog弹窗
                this.$emit('closeMenuDialog')
                this.$message({
                  message: '保存成功',
                  type: 'success'
                })
              } else {
                this.$alert(
                  response.message,
                  {type: 'error', lockScroll: false, confirmButtonText: '确定'}
                )
              }
            })
          } else {
            // 如果没有menuInfoId，新增菜单信息
            addMenu(this.form).then(response => {
              if (response.code === 10000) {
                // 保存成功后关闭dialog弹窗
                this.$emit('closeMenuDialog')
                this.$message({
                  message: '保存成功',
                  type: 'success'
                })
              } else {
                this.$alert(
                  response.message,
                  {type: 'error', lockScroll: false, confirmButtonText: '确定'}
                )
              }
            })
          }
        }
      })
    }
  }
}
</script>

<style scoped>
.input-width {
  width: 350px;
}
</style>
