import Vue from 'vue'
import VueRouter from 'vue-router'
import {store} from "core-js/internals/reflect-metadata";
// import homepage from "@/views/homepage/homepage";
// import parents from "@/views/parents/parents";
// import teachers from "@/views/teachers/teachers";
// import admin from "@/views/admin/admin";
// import relationship from "@/views/parents/chil/relationship";
// import detail from "@/views/parents/chil/detail";
// import task from "@/views/common/task";
// import addstudent from "@/views/parents/chil/addstudent";
// import mytask from "@/views/parents/chil/mytask";
// import taskhome from "@/views/common/taskhome";
// import addhometask from "@/views/common/addhometask";
// import parnotice from "@/views/parents/chil/parnotice";
// import teastudent from "@/views/teachers/chil/teastudent";
// import teadetail from "@/views/teachers/chil/teadetail";
// import teachertaskhome from "@/views/teachers/chil/teachertaskhome";
// import schtask from "@/views/teachers/chil/schtask";
// import schtask2 from "@/views/teachers/chil/schtask2";
// import teanotice from "@/views/teachers/chil/teanotice";
// import addnotice from "@/views/teachers/chil/addnotice";
// import addschooltask from "@/views/common/addschooltask";
// import astudent from "@/views/admin/chil/astudent";
// import ateacher from "@/views/admin/chil/ateacher";
// import aparent from "@/views/admin/chil/aparent";
// import addstudenta from "@/views/admin/chil/addstudenta";
// import addteachera from "@/views/admin/chil/addteachera";
// import addparenta from "@/views/admin/chil/addparenta";
import storage from'@/storage'
Vue.use(VueRouter)

const routes = [
  {
    path:'',
    redirect:'/homepage'
  },
  {
    path: '/homepage',
    name:'homepage',
    component:()=>import('@/views/homepage/homepage.vue'),
    meta: { identity: 0}
  },
  {
    path: '/parents',
    name:'parents',
    component:()=>import('@/views/parents/parents.vue'),
    meta: { identity: 2},
    children:[
      {
        path: '/parents',
        redirect:'/parents/relationship'
      },
      {
        path:'/parents/relationship',
        name:'relationship',
        component:()=>import('@/views/parents/chil/relationship.vue'),
        meta: { identity: 2}
      },
      {
        path:'/parents/detail',
        name:'detail',
        component:()=>import('@/views/parents/chil/detail.vue'),
        meta: { identity: 2}
      },
      {
        path: '/parents/task',
        name: 'task',
        component:()=>import('@/views/common/task.vue'),
        meta: { identity: 2}
      },
      {
        path: '/parents/addstudent',
        name:'addstudent',
        component:()=>import('@/views/parents/chil/addstudent.vue'),
        meta: { identity: 2}
      },
      {
        path: '/parents/mytask',
        name:'mytask',
        component:()=>import('@/views/parents/chil/mytask.vue'),
        meta: { identity: 2}
      },
      {
        path:'/parents/taskhome',
        name:'taskhome',
        component:()=>import('@/views/common/taskhome.vue'),
        meta: { identity: 2}
      },
      {
        path: '/parents/addhometask',
        name:'addhometask',
        component:()=>import('@/views/common/addhometask.vue'),
        meta: { identity: 2}
      },
      {
        path: '/parents/parnotice',
        name:'parnotice',
        component:()=>import('@/views/parents/chil/parnotice.vue'),
        meta: { identity: 2}
      }

    ]
  },
  {
    path: '/teachers',
    name: 'teachers',
    component:()=>import('@/views/teachers/teachers.vue'),
    meta: { identity: 1},
    children: [
      {
        path: '/teachers',
        redirect: '/teachers/teastudent'
      },
      {
        path: '/teachers/teastudent',
        name: 'teastudent',
        component:()=>import('@/views/teachers/chil/teastudent.vue'),
        meta: { identity: 1}
      },
      {
        path: '/teachers/teadetail',
        name:'teadetail',
        component:()=>import('@/views/teachers/chil/teadetail.vue'),
        meta: { identity: 1}
      },
      {
        path:'/teachers/teachertaskhome',
        name: 'teachertaskhome',
        component:()=>import('@/views/teachers/chil/teachertaskhome.vue'),
        meta: { identity: 1}
      },
      {
        path: '/teachers/schtask',
        name: 'schtask',
        component:()=>import('@/views/teachers/chil/schtask.vue'),
        meta: { identity: 1}
      },
      {
        path: '/teachers/sctask2',
        name:'sctask2',
        component:()=>import('@/views/teachers/chil/schtask2.vue'),
        meta: { identity: 1}
      },
      {
        path: '/teachers/teanotice',
        name: 'teanotice',
        component:()=>import('@/views/teachers/chil/teanotice.vue'),
        meta: { identity: 1}
      },
      {
        path: '/teachers/addnotice',
        name:'addnotice',
        component:()=>import('@/views/teachers/chil/addnotice.vue'),
        meta: { identity: 1}
      },
      {
        path: '/teachers/addschooltask',
        name: 'addschooltask',
        component:()=>import('@/views/common/addschooltask.vue'),
        meta: { identity: 1}
      }
    ]
  },
  {
    path: '/admin',
    name:'admin',
    component:()=>import('@/views/admin/admin.vue'),
    meta: { identity: 3},
    children: [
      {
        path: '/admin',
        redirect: '/admin/astudent'
      },
      {
        path: '/admin/astudent',
        name: 'astudent',
        component:()=>import('@/views/admin/chil/astudent.vue'),
        meta: { identity: 3}

      },
      {
        path: '/admin/ateacher',
        name: 'ateacher',
        component:()=>import('@/views/admin/chil/ateacher.vue'),
        meta: { identity: 3}
      },
      {
        path: '/admin/aparent',
        name:'aparent',
        component:()=>import('@/views/admin/chil/aparent.vue'),
        meta: { identity: 3}
      },
      {
        path: '/admin/addstudenta',
        name: 'addstudenta',
        component:()=>import('@/views/admin/chil/addstudenta.vue'),
        meta: { identity: 3}
      },
      {
        path:'/admin/addteachera',
        name:'addteachera',
        component:()=>import('@/views/admin/chil/addteachera.vue'),
        meta: { identity: 3}
      },
      {
        path: '/admin/addparenta',
        name:'addparenta',
        component:()=>import('@/views/admin/chil/addparenta.vue'),
        meta: { identity: 3}
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach(function (to, from, next){

  if (storage.get('jwt')==''){
    if(to.path!='/homepage'){
      next('/homepage')
    }else {
      next()
    }
  }else{
    if (to.meta.identity==0){
      next()
    }else{
      if(to.meta.identity!=storage.get('identity')){
        next('/homepage')
      }else {
        next()
      }
    }

  }
})

export default router
