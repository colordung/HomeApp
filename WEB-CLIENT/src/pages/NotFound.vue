<template>
  <div class="container">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12 text-center">
          <img src="/static/img/404-error.png" width="787" class="margin-bottom">
          <p class="margin-bottom">{{'NotFound'}}</p>
          <!-- 관리자 로그인 -->
          <router-link to="/"><button type="submit" class="btn btn-primary">{{'Home'}}</button></router-link>
          <!-- 로그인 -->
        </div>
      </div>
    </section>
    <!-- /Main content -->
  </div>
</template>

<script>
	export default {
		name: "NotFound"
	}
</script>

<style scoped>

</style>
