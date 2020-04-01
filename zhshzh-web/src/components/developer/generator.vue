<template>
  <div>
    <el-form :model="form" :rules="rules" ref="form" label-width="160px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="数据库名" prop="tableSchema" >
            <el-input v-model.trim="form.tableSchema" clearable class="input-width" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="数据库表名" prop="tableName" >
            <el-autocomplete
              v-model.trim="form.tableName"
              :fetch-suggestions="querySearch"
              clearable class="input-width" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="po文件路径" :prop="form.generatePoFile?'poFilePath':'noPoRule'" >
            <el-input v-model.trim="form.poFilePath" clearable class="input-width" @input="poChange" :disabled="!form.generatePoFile" >
              <template slot="prepend">{{javaPrefix}}</template>
              <template slot="append">.po</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否生成">
            <el-radio-group v-model="form.generatePoFile" @change="$refs['form'].clearValidate('noPoRule')" >
              <el-radio :label=true>生成</el-radio>
              <el-radio :label=false>不生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="dao文件路径" :prop="form.generateDaoFile?'daoFilePath':'noDaoRule'" >
            <el-input v-model.trim="form.daoFilePath" clearable class="input-width" :disabled="!form.generateDaoFile" >
              <template slot="prepend">{{javaPrefix}}</template>
              <template slot="append">.dao</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否生成" >
            <el-radio-group v-model="form.generateDaoFile" @change="$refs['form'].clearValidate('noDaoRule')" >
              <el-radio :label=true>生成</el-radio>
              <el-radio :label=false>不生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="mapper文件路径" :prop="form.generateMapperFile?'mapperFilePath':'noMapperRule'" >
            <el-input v-model.trim="form.mapperFilePath" clearable class="input-width" :disabled="!form.generateMapperFile" >
              <template slot="prepend">{{mapperPrefix}}</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否生成" >
            <el-radio-group v-model="form.generateMapperFile" @change="$refs['form'].clearValidate('noMapperRule')" >
              <el-radio :label=true>生成</el-radio>
              <el-radio :label=false>不生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="dto文件路径" :prop="form.generateDtoFile?'dtoFilePath':'noDtoRule'" >
            <el-input v-model.trim="form.dtoFilePath" clearable class="input-width" :disabled="!form.generateDtoFile" >
              <template slot="prepend">{{javaPrefix}}</template>
              <template slot="append">.dto</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否生成" >
            <el-radio-group v-model="form.generateDtoFile" @change="$refs['form'].clearValidate('noDtoRule')" >
              <el-radio :label=true>生成</el-radio>
              <el-radio :label=false>不生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="service文件路径" :prop="form.generateServiceFile?'serviceFilePath':'noServiceRule'" >
            <el-input v-model.trim="form.serviceFilePath" clearable class="input-width" :disabled="!form.generateServiceFile" >
              <template slot="prepend">{{javaPrefix}}</template>
              <template slot="append">.service</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否生成" >
            <el-radio-group v-model="form.generateServiceFile" @change="$refs['form'].clearValidate('noServiceRule')" >
              <el-radio :label=true>生成</el-radio>
              <el-radio :label=false>不生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="controller文件路径" :prop="form.generateControllerFile?'controllerFilePath':'noControllerRule'" >
            <el-input v-model.trim="form.controllerFilePath" clearable class="input-width" :disabled="!form.generateControllerFile" >
              <template slot="prepend">{{javaPrefix}}</template>
              <template slot="append">.controller</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否生成" >
            <el-radio-group v-model="form.generateControllerFile" @change="generateControllerFile" >
              <el-radio :label=true>生成</el-radio>
              <el-radio :label=false>不生成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="requestMapping" :prop="form.generateControllerFile?'requestMapping':'noRequestMappingRule'" >
            <el-input v-model.trim="form.requestMapping" clearable class="input-width" :disabled="!form.generateControllerFile" >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
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
      javaPrefix: 'cn.com.zhshzh.',
      mapperPrefix: 'mybatis/mapper/',
      tableNames: [],
      form: {
        tableSchema: 'daily',
        tableName: '',
        poFilePath: '',
        generatePoFile: true,
        daoFilePath: '',
        generateDaoFile: true,
        mapperFilePath: '',
        generateMapperFile: true,
        dtoFilePath: '',
        generateDtoFile: false,
        serviceFilePath: '',
        generateServiceFile: false,
        controllerFilePath: '',
        generateControllerFile: false,
        requestMapping: ''
      },
      rules: {
        tableSchema: [
          { required: true, message: '必填项', trigger: 'blur' }
        ],
        tableName: [
          { required: true, message: '必填项', trigger: 'change' }
        ],
        poFilePath: [
          { required: true, message: '必填项', trigger: ['blur', 'change'] }
        ],
        daoFilePath: [
          { required: true, message: '必填项', trigger: ['blur', 'change'] }
        ],
        mapperFilePath: [
          { required: true, message: '必填项', trigger: ['blur', 'change'] }
        ],
        dtoFilePath: [
          { required: true, message: '必填项', trigger: ['blur', 'change'] }
        ],
        serviceFilePath: [
          { required: true, message: '必填项', trigger: ['blur', 'change'] }
        ],
        controllerFilePath: [
          { required: true, message: '必填项', trigger: ['blur', 'change'] }
        ],
        requestMapping: [
          { required: true, message: '必填项1', trigger: ['blur', 'change'] }
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
            poFilePath: this.javaPrefix + this.form.poFilePath + '.po',
            daoFilePath: this.javaPrefix + this.form.daoFilePath + '.dao',
            mapperFilePath: this.mapperPrefix + this.form.mapperFilePath,
            dtoFilePath: this.javaPrefix + this.form.dtoFilePath + '.dto',
            serviceFilePath: this.javaPrefix + this.form.serviceFilePath + '.service',
            controllerFilePath: this.javaPrefix + this.form.controllerFilePath + '.controller',
            requestMapping: this.form.requestMapping,
            generatePoFile: this.form.generatePoFile,
            generateDaoFile: this.form.generateDaoFile,
            generateMapperFile: this.form.generateMapperFile,
            generateDtoFile: this.form.generateDtoFile,
            generateServiceFile: this.form.generateServiceFile,
            generateControllerFile: this.form.generateControllerFile
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
    },
    /**
     * 自动填充
     * @param val
     */
    poChange (val) {
      this.form.daoFilePath = val
      this.form.dtoFilePath = val
      this.form.serviceFilePath = val
      this.form.controllerFilePath = val
    },
    /**
     * 是否生成controller文件的change事件
     * @param val
     */
    generateControllerFile (val) {
      if (!val) {
        this.$refs['form'].clearValidate('noControllerRule')
        this.$refs['form'].clearValidate('noRequestMappingRule')
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
