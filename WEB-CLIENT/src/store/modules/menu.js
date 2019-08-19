//initial state
const state = {
    menus: [],
    allMenus: [],
};

//getters
const getters = {
    isMenus: (state, getters) => {
        return state.menus.length > 0;
    },
    getUserGrantMapps: (state) => {
        return state.user;
    },
};

//actions
const actions = {
    getMenus({ commit }) {
        this.$axios.get(`/api/system/menu/menuList`).then(result => {
            commit('setMenus', result.data);
        });
    },
    getAllMenus({ commit }) {
        this.$axios.get('/api/system/menu/menuAllList').then(result => {
            commit('setAllMenus', result.data);
        });
    }
};

//mutations
const mutations = {
    setMenus(state, menus) {
        if (!!sessionStorage.token) {
            state.menus = menus;
        } else {
            state.menus = [];
        }
    },
    setAllMenus(state, menus) {
        if (!!sessionStorage.token) {
            state.allMenus = menus;
        } else {
            state.allMenus = [];
        }
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};