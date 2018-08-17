const menu = {
  state: {
    menus: []
  },

  mutations: {
    SET_MENUS: (state, routers) => {
      state.menus = routers
    }
  }
}

export default menu
