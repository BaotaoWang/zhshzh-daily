<template>
  <div>
    <el-form :model="form" label-width="80px">
      <el-form-item label="父级菜单" required >
        <el-cascader
          v-model="form.parentId"
          :options="parentOptions"
          :props="{ expandTrigger: 'hover',checkStrictly: true }"
          :show-all-levels="false"
          @change="parentChange"
          class="input-width" />
      </el-form-item>
      <el-form-item label="菜单名称" required prop="menuName" >
        <el-input v-model="form.menuName" clearable class="input-width" />
      </el-form-item>
      <el-form-item label="菜单路由" prop="menuRoute" >
        <el-input v-model="form.menuRoute" clearable class="input-width" />
      </el-form-item>
      <el-form-item label="菜单序号" required >
        <el-input-number
          :min="1"
          :max="100"
          v-model="form.menuOrder"
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
    </el-form>
  </div>
</template>

<script>
import {listCascaderSysMenuInfos} from '@/http/api'

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
      }
    }
  },
  props: ['menuInfo'],
  mounted () {
    this.form.menuInfoId = this.menuInfo.menuInfoId
    // parentOptions中value是string类型，而parentId是int类型，不转字符串的话，页面初始化的时候不会回显。不能用toString，有可能会空指针
    this.form.parentId = this.menuInfo.parentId + ''
    this.form.menuName = this.menuInfo.menuName
    this.form.menuRoute = this.menuInfo.menuRoute
    this.form.menuOrder = this.menuInfo.menuOrder
    this.form.menuIcon = this.menuInfo.menuIcon
    this.form.menuDescription = this.menuInfo.menuDescription
    // 查询菜单树
    this.listCascaderSysMenuInfos()
  },
  destroyed () {
    // 子组件销毁时，将form表单中的数组传给父组件，并触发父组件的保存方法，是否真保存，在方法中有进一步判断
    this.$emit('saveMenu', this.form)
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
    }
  }
}
</script>

<style scoped>
.input-width {
  width: 350px;
}
</style>
