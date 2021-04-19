<template>
    <div>
        <paginate
            name="languages"
            :list="langs"
            :per="2"
        >
            <li v-for="(lang, index) in paginated('languages')" :key="index">
                {{ lang.lan }}
            </li>
        </paginate>
        <paginate-links for="languages"></paginate-links>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                langs: [{lan: 'JavaScript', age: 11}, {lan: 'PHP', age: 12}, {lan: 'HTML', age: 13}],
                paginate: ['languages']
            }
        }
    }
</script>

<style scoped>

</style>