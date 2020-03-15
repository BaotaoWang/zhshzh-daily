<template>
  <div>
    <el-form :model="form" :rules="rules" ref="form" label-width="140px">
      <el-form-item label="数据库名" prop="tableSchema" >
        <el-input v-model.trim="form.tableSchema" clearable class="input-width" />
      </el-form-item>
      <el-form-item label="数据库表名" prop="tableName" >
        <el-autocomplete
          v-model.trim="form.tableName"
          :fetch-suggestions="querySearch"
          clearable class="input-width" />
      </el-form-item>
      <el-form-item label="po文件路径" prop="poFilePath" >
        <el-input v-model.trim="form.poFilePath" clearable class="input-width" >
          <template slot="prepend">{{poPrefix}}</template>
        </el-input>
      </el-form-item>
      <el-form-item label="dao文件路径" prop="daoFilePath" >
        <el-input v-model.trim="form.daoFilePath" clearable class="input-width" >
          <template slot="prepend">{{poPrefix}}</template>
        </el-input>
      </el-form-item>
      <el-form-item label="mapper文件路径" prop="mapperFilePath" >
        <el-input v-model.trim="form.mapperFilePath" clearable class="input-width" >
          <template slot="prepend">{{mapperPrefix}}</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="generator()" type="primary">确 定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {generator, listTableNames} from '@/http/api'

export default {
  name: 'generator',
  data () {
    return {
      poPrefix: 'cn.com.zhshzh.',
      mapperPrefix: 'mybatis/mapper/',
      tableNames: [],
      form: {
        tableSchema: 'daily',
        tableName: '',
        poFilePath: '',
        daoFilePath: '',
        mapperFilePath: ''
      },
      rules: {
        tableSchema: [
          { required: true, message: '必填项', trigger: 'blur' }
        ],
        tableName: [
          { required: true, message: '必填项', trigger: 'change' }
        ],
        poFilePath: [
          { required: true, message: '必填项', trigger: 'blur' }
        ],
        daoFilePath: [
          { required: true, message: '必填项', trigger: 'blur' }
        ],
        mapperFilePath: [
          { required: true, message: '必填项', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.listTableNames(this.form.tableSchema)
  },
  methods: {
    /**
     * 查询数据库中所有的表名
     */
    listTableNames (tableSchema) {
      let schemaData = {
        tableSchema: tableSchema
      }
      listTableNames(schemaData).then(response => {
        if (response.code === 10000) {
          this.tableNames = response.data
        }
      })
    },
    /**
     * 生成代码
     */
    generator () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let finalData = {
            tableSchema: this.form.tableSchema,
            tableName: this.form.tableName,
            poFilePath: this.poPrefix + this.form.poFilePath,
            daoFilePath: this.poPrefix + this.form.daoFilePath,
            mapperFilePath: this.mapperPrefix + this.form.mapperFilePath
          }
          generator(finalData).then(response => {
            if (response.code === 10000) {
              this.$message({
                message: '代码生成完毕',
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
      })
    },
    querySearch (queryString, callback) {
      let tableNames = this.tableNames
      let results = queryString ? tableNames.filter(this.createFilter(queryString)) : tableNames
      // 调用 callback 返回建议列表的数据
      callback(results)
    },
    createFilter (queryString) {
      return (tableNames) => {
        return (tableNames.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    }
  }
}
</script>

<style scoped>
.input-width {
  width: 400px;
}
</style>
