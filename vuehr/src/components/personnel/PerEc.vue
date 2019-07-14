<template>
  <div>
    <h1>员工奖惩登记</h1>
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle"
               style="padding: 0px;"
               :close-on-click-modal="false"
               width="33%"></el-dialog>
    <div style="text-align: center; margin-top: 50px; margin-left: 40%">
      <el-form :model="ec" :rules="ecrules" ref="addReasonForm">
        <div>
          <el-row>
            <el-col :span="5">
              <div>
                <el-form-item label="员工号:" prop="eid">
                  <el-input prefix-icon="el-icon-edit" v-model="ec.eid" size="mini" style="width: 300px"
                            placeholder="请输入员工号"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <div>
                <el-form-item label="奖罚日期:" prop="ecDate">
                  <el-date-picker
                    v-model="ec.ecDate"
                    size="mini"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    style="width: 300px"
                    type="date"
                    placeholder="奖罚日期">
                  </el-date-picker>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <div>
                <el-form-item label="奖罚原因:" prop="ecReason">
                  <el-radio-group v-model="ec.ecReason">
                    <el-radio label="迟到">迟到</el-radio>
                    <el-radio style="margin-left: 15px" label="请假">请假</el-radio>
                    <el-radio style="margin-left: 15px" label="加班">加班</el-radio>
                  </el-radio-group>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <div>
                <el-form-item label="奖罚类别:" prop="ecType">
                  <el-radio-group v-model="ec.ecType">
                    <el-radio label="0">奖励</el-radio>
                    <el-radio style="margin-left: 15px" label="1">罚款</el-radio>
                  </el-radio-group>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <div>
                <el-form-item label="备注:" prop="remark">
                  <el-input
                    type="textarea"
                    size="mini"
                    style="width: 300px;"
                    :autosize="{ minRows: 5, maxRows: 10}"
                    placeholder="请输入备注内容..."
                    v-model="ec.remark"></el-input>
                </el-form-item>
              </div>
              <div style="margin-left: 240px">
                <el-button size="mini" @click="addEc('addReasonForm')" >确定</el-button>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return{
        dialogTitle: '成功',
        dialogVisible: false,
        ec:{
          eid:'',
          ecDate:'',
          ecReason:'',
          ecType:'',
          remark:''
        },
        ecrules:{
          name: [{required: true, message: '必填:员工姓名', trigger: 'blur'}],
          workID: [{required: true, message: '必填:员工编号', trigger: 'blur'}],
          ecDate: [{required: true, message: '必填:奖罚日期', trigger: 'blur'}],
          ecReason: [{required: true, message: '必填:奖罚原因', trigger: 'blur'}],
          ecType: [{required: true, message: '必填:奖罚原因', trigger: 'blur'}]
        }
      };
    },
    methods:{
      addEc(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
              //添加
              this.postRequest("/personnel/ec/addec", this.ec).then(resp=> {
                if (resp && resp.status == 200) {
                  var data = resp.data;

                  _this.dialogVisible = false;
                  _this.emptyEcData();
                }
              })
          } else {
            return false;
          }
        });
      },
      emptyEcData(){
        this.ec={
          eid:'',
          ecDate:'',
          ecReason:'',
          ecType:'',
          remark:''
        }
      }
    }
  };
</script>
