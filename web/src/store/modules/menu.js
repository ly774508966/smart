const menu = {
  state: {
    menus: []
  },

  mutations: {
    SET_MENUS: (state, routers) => {
      console.log("【store】-> SET_MENUS")
      state.menus = routers
    }
  }
}

export default menu
