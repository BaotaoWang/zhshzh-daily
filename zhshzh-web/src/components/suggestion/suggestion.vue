<template>
  <div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="createTime" label="时间" min-width="15%"></el-table-column>
      <el-table-column prop="content" label="内容" min-width="25%"></el-table-column>
      <el-table-column prop="status" label="状态" min-width="10%"></el-table-column>
      <el-table-column prop="reply" label="回复" min-width="25%"></el-table-column>
      <el-table-column prop="replyUserName" label="回复人" min-width="10%"></el-table-column>
      <el-table-column prop="updateTime" label="回复时间" min-width="15%"></el-table-column>
    </el-table>
    <div style="margin-top: 10px;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        background
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'suggestion',
  data () {
    return {
      tableData: [
        {
          createTime: '2019-11-24 12:00:00',
          content: '发现了一个bug',
          status: '新增',
          reply: '',
          replyUserName: '',
          updateTime: ''
        },
        {
          createTime: '2019-11-23 12:00:00',
          content: '无法登录',
          status: '处理中',
          reply: '该问题正在处理中',
          replyUserName: '系统管理员',
          updateTime: '2019-11-23 22:00:00'
        },
        {
          createTime: '2019-11-22 12:00:00',
          content: '日志无法保存',
          status: '已处理',
          reply: '已处理该问题',
          replyUserName: '系统管理员',
          updateTime: '2019-11-22 22:00:00'
        },
        {
          createTime: '2019-11-21 12:00:00',
          content: '乱码了',
          status: '已驳回',
          reply: '描述不清',
          replyUserName: '系统管理员',
          updateTime: '2019-11-21 22:00:00'
        }
      ],
      currentPage: 1,
      pageSize: 10,
      total: 4
    }
  },
  methods: {
    handleSizeChange (value) {
      this.pageSize = value
      console.log('handleSizeChange')
      console.log(`每页 ${this.pageSize} 条`)
      console.log(`当前页： ${this.currentPage} `)
    },
    handleCurrentChange (value) {
      this.currentPage = value
      console.log('handleCurrentChange')
      console.log(`每页 ${this.pageSize} 条`)
      console.log(`当前页： ${this.currentPage} `)
    }
  }
}
</script>

<style scoped>

</style>
