import Vue from 'vue'
import Vuex from 'vuex'
import storage from'@/storage'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //存放token
    jwt:'',
    //存放身份识别，1是老师，2是家长，3是管理员
    identity:0,
  },
  mutations: {
    set_jwt(state,jwt){
      state.jwt=jwt;
      storage.set('jwt',jwt);
    },
    set_identity(state,identity){
      state.identity=identity;
      storage.set('identity',identity);
    },
    del_all(state){
      state.identity=0;
      state.jwt='';
      storage.removeAll();
    }
  },
  getters: {
    get_jwt(){
      return storage.get('jwt');
    },
    get_identity(){
      return storage.get('identity');
    }
  },
  actions: {
  },
  modules: {
  }
})
