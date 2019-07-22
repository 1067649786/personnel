<template>
  <div>
    <el-container>
      <el-header style="display: flex;justify-content: space-between;align-items: center;padding-left: 0px">
        <el-button @click="dialogVisible = true" icon="el-icon-plus" type="primary" size="mini">添加调薪</el-button>
        <el-button size="mini" type="success" @click="loadSalaryCfg" icon="el-icon-refresh"></el-button>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="salaries"
            stripe
            v-loading="tableLoading"
            size="mini"
            @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column
              type="selection"
              width="30">
            </el-table-column>
            <el-table-column
              width="120"
              prop="eid"
              label="员工编号">
            </el-table-column>
            <el-table-column
              width="100"
              label="调薪日期">
              <template slot-scope="scope">{{ scope.row.asDate | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="70"
              prop="beforeSalary"
              label="调前薪资">
            </el-table-column>
            <el-table-column
              width="70"
              prop="afterSalary"
              label="调后薪资">
            </el-table-column>
            <el-table-column
              width="350"
              prop="reason"
              label="调薪原因">
            </el-table-column>
            <el-table-column
              prop="remark"
              width="350"
              label="备注">
            </el-table-column>

            <el-table-column label="操作" align="center">
              <el-table-column label="编辑" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑
                  </el-button>
                </template>
              </el-table-column>
              <el-table-column label="删除" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
        </div>
        <div style="text-align: left;margin-top: 10px" v-if="salaries!=0">
          <el-button type="danger" round size="mini" :disabled="multipleSelection.length==0" @click="deleteAll">批量删除
          </el-button>
        </div>
      </el-main>
    </el-container>
    <div style="text-align: left">
      <el-dialog
        title="添加调薪"
        :visible.sync="dialogVisible"
        width="50%"
        @close="emptySalary"
        :close-on-click-modal="false">
        <div style="display: flex;justify-content: flex-start;">
          <el-steps :active="index" direction="vertical">
            <el-step title="员工编号" size="mini"></el-step>
            <el-step title="调薪日期"></el-step>
            <el-step title="调前薪资"></el-step>
            <el-step title="调后薪资"></el-step>
            <el-step title="调薪原因"></el-step>
            <el-step title="备注"></el-step>
          </el-steps>
          <div style="margin-left: 30px;display: flex;justify-content: center;align-items: center;width: 80%;">
            <div v-show="index==0">
              员工编号：
              <el-input
                placeholder="请输入员工编号..."
                size="mini"
                style="width: 200px"
                type="number"
                @keyup.enter.native="next"
                v-model="salary.eid">
              </el-input>
            </div>
            <div v-show="index==1">
              调薪日期：
              <el-date-picker
                v-model="salary.asDate"
                type="date"
                size="mini"
                value-format="yyyy-MM-dd"
                style="width: 200px;"
                placeholder="选择日期">
              </el-date-picker>
            </div>
            <div v-show="index==2">
              调前薪资：
              <el-input
                placeholder="请输入调前薪资..."
                @keyup.enter.native="next"
                size="mini"
                style="width: 200px"
                type="number"
                v-model="salary.beforeSalary">
              </el-input>
            </div>
            <div v-show="index==3">
              调后薪资：
              <el-input
                placeholder="请输入调后薪资..."
                @keyup.enter.native="next"
                size="mini"
                style="width: 200px"
                type="number"
                v-model="salary.afterSalary">
              </el-input>
            </div>
            <div v-show="index==4">
              调薪原因：
              <el-input
                placeholder="请输入调薪原因..."
                size="mini"
                @keyup.enter.native="next"
                style="width: 200px"
                type="textarea"
                v-model="salary.reason">
              </el-input>
            </div>
            <div v-show="index==5">
              备注：
              <el-input
                placeholder="请输入备注..."
                size="mini"
                @keyup.enter.native="next"
                style="width: 200px"
                type="textarea"
                v-model="salary.remark">
              </el-input>
            </div>
          </div>
        </div>
        <div style="display: flex;align-items: center;justify-content: center;padding: 0px;margin: 0px;">
          <el-button round size="mini" v-if="index!=0" @click="index--">上一步</el-button>
          <el-button type="primary" round size="mini" @click="next" v-text="index==5?'完成':'下一步'"></el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        dialogVisible: false,
        tableLoading: false,
        index: 0,
        salaries: [],
        multipleSelection: [],
        salary: {
          id: '',
          eid:'',
          asDate:'',
          beforeSalary:'',
          afterSalary:'',
          reason:'',
          remark:''
        }
      };
    },
    methods: {
      deleteAll(){
        this.$confirm('删除[' + this.multipleSelection.length + ']条记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          this.multipleSelection.forEach(row=> {
            ids = ids + row.id + ",";
          })
          this.doDelete(ids);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleEdit(index, row) {
        this.dialogVisible = true;
        row.asDate = new Date(row.asDate);
        this.salary = row;
      },
      handleDelete(index, row) {
        this.$confirm('删除[' + row.eid + ']账套, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      doDelete(id){
        var _this = this;
        _this.tableLoading = true;
        this.deleteRequest("/personnel/adjust/salary/" + id).then(resp=>{
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;

            _this.loadSalaryCfg();
          }
        });
      },
      next(){
        var _this = this;
        if (this.index == 5) {
          if(this.salary.eid&&this.salary.asDate&&this.salary.beforeSalary&&this.salary.afterSalary&&this.salary.reason&&this.salary.remark){
            if (this.salary.id) {//更新
              _this.tableLoading = true;
              this.putRequest("/personnel/adjust/salary", this.salary).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _
                  _this.dialogVisible = false;
                  _this.index = 0;
                  _this.loadSalaryCfg();
                }
              });
            } else {//添加
                this.postRequest("/personnel/adjust/salary", this.salary).then(resp=> {
                  if (resp && resp.status == 200) {
                    var data = resp.data;

                    _this.dialogVisible = false;
                    _this.index = 0;
                    _this.loadSalaryCfg();
                  }
                });
            }
          }else{
            this.$message({type: 'error', message: '字段不能为空!'});
          }
        } else {
          this.index++;
        }
      },
      loadSalaryCfg(){
        this.tableLoading = true;
        var _this = this;
        this.getRequest("/personnel/adjust/salary").then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        })
      },
      emptySalary(){
        this.salary = {
          id: '',
          eid:'',
          asDate:'',
          beforeSalary:'',
          afterSalary:'',
          reason:'',
          remark:''
        };
        this.index = 0;
      }
    },
    mounted: function () {
      this.loadSalaryCfg();
    }
  };
</script>
<style>
  .el-dialog__body {
    padding-bottom: 10px;
  }
</style>
